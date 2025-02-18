package com.maidao.edu.news.common.file.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.maidao.edu.news.common.file.entity.AliUploadToken;
import com.maidao.edu.news.common.file.entity.FileConfig;
import com.maidao.edu.news.common.file.entity.UploadOptions;
import com.maidao.edu.news.common.file.entity.UploadToken;
import com.maidao.edu.news.common.util.*;
import com.sunnysuperman.commons.util.FileUtil;
import com.sunnysuperman.commons.util.JSONUtil;
import com.sunnysuperman.commons.util.PlaceholderUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:FileService
 * 类描述:文件上传服务类
 **/
@Service
public class FileService {
    private static final int MAX_UPLOAD_SIZE = 5 * 1024 * 1024;
    private static final String STS_API_VERSION = "2015-04-01";
    private static final UUIDCreatorFactory.UUIDCreator TMP_FILE_ID_CREATOR = UUIDCreatorFactory.get();
    private String POLICY_TEMPLATE;
    @Autowired
    private FileConfig ossFConfig;
    private OSSClient ossClient;
    private final UUIDCreatorFactory.UUIDCreator ossFileNameCreator = UUIDCreatorFactory.get();

    private static AssumeRoleResponse assumeRole(String accessKeyId, String accessKeySecret, String roleArn,
                                                 String roleSessionName, String policy, int expireSeconds) throws Exception {
        // 创建一个 Aliyun Acs Client, 用于发起 OpenAPI 请求
        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        // 创建一个 AssumeRoleRequest 并设置请求参数
        final AssumeRoleRequest request = new AssumeRoleRequest();
        request.setVersion(STS_API_VERSION);
        request.setMethod(MethodType.POST);
        request.setProtocol(ProtocolType.HTTPS);
        request.setRoleArn(roleArn);
        request.setRoleSessionName(roleSessionName);
        request.setPolicy(policy);
        request.setDurationSeconds((long) expireSeconds);
        // 发起请求，并得到response
        return client.getAcsResponse(request);
    }

    @PostConstruct
    public void init() throws Exception {
        POLICY_TEMPLATE = FileUtil.read(R.getStream("resources/file_ali_policy.json"));
        POLICY_TEMPLATE = JSONUtil.toJSONString(JSONUtil.parse(POLICY_TEMPLATE));
        ossClient = new OSSClient(ossFConfig.getInternalEndpoint(), ossFConfig.getKey(), ossFConfig.getSecret());
    }

    private String generateObjectKey(String namespace, String fileName, int randomLength) {
        if (StringUtils.isEmpty(namespace)) {
            throw new IllegalArgumentException("namespace");
        }
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        StringBuilder buf = new StringBuilder();
        if (ossFConfig.getNamespace() != null) {
            buf.append(ossFConfig.getNamespace()).append('/');
        }
        buf.append(namespace).append('/').append(year).append('/').append(month).append('/').append(day).append('/')
                .append(ossFileNameCreator.create());
        if (randomLength > 0) {
            buf.append(StringUtils.randomString(StringUtils.allstrs, randomLength));
        }
        String ext = FileUtil.getFileExt(fileName);
        if (ext != null) {
            buf.append('.').append(ext);
        }
        return buf.toString();
    }

    public String upload(File file, UploadOptions options) throws Exception {
        ObjectMetadata metadata = new ObjectMetadata();
        String contentType = options.getContentType();
        if (contentType == null) {
            contentType = Files.probeContentType(file.toPath());
        }
        if (contentType != null) {
            metadata.setContentType(contentType);
        }
        if (options.getPermission() != null) {
            switch (options.getPermission()) {
                case PRIVATE:
                    metadata.setObjectAcl(CannedAccessControlList.Private);
                    break;
                case PUBLIC_READ:
                    metadata.setObjectAcl(CannedAccessControlList.PublicReadWrite);
                    break;
                default:
                    break;
            }
        }
        String fileName = options.getFileName() != null ? options.getFileName() : file.getName();
        String objectKey = generateObjectKey(options.getNamespace(), fileName, options.getRandomLength());
        ossClient.putObject(ossFConfig.getBucket(), objectKey, file, metadata);
        return "http://" + ossFConfig.getCanonicalDomain() + "/" + objectKey;
    }

    public UploadToken uploadToken(String namespace, String fileName, int fileSize, boolean cdn) throws Exception {
        if (fileSize > MAX_UPLOAD_SIZE) {
            throw new ServiceException("超过最大上传文件大小");
        }
        String objectKey = generateObjectKey(namespace, fileName, 8);
        String policy;
        {
            Map<String, Object> context = new HashMap<>(2);
            context.put("bucket", ossFConfig.getBucket());
            context.put("objectKey", objectKey);
            policy = PlaceholderUtil.compile(POLICY_TEMPLATE, context);
        }
        AssumeRoleResponse response = assumeRole(ossFConfig.getKey(), ossFConfig.getSecret(), ossFConfig.getPutArn(),
                "adm", policy, 1800);
        AliUploadToken token = new AliUploadToken();
        token.setVendor("ali");
        token.setAccessKey(response.getCredentials().getAccessKeyId());
        token.setAccessSecret(response.getCredentials().getAccessKeySecret());
        token.setStsToken(response.getCredentials().getSecurityToken());
        token.setRegion(ossFConfig.getRegion());
        token.setEndpoint("https://oss-" + ossFConfig.getRegion() + ".aliyuncs.com");

        token.setBucket(ossFConfig.getBucket());
        token.setKey(objectKey);
        token.setUrl("http://" + (cdn ? ossFConfig.getCdnDomain() : ossFConfig.getCanonicalDomain()) + "/" + objectKey);
        return token;
    }

    public String makeSignedUrl(String objectKey, int expireSeconds, boolean cdn) throws Exception {
        Date expiration = new Date(System.currentTimeMillis() + 1000L * expireSeconds);
        String signedUrl = ossClient.generatePresignedUrl(ossFConfig.getBucket(), objectKey, expiration).toString();
        String finalUrl = URLUtils.replaceDomain(signedUrl,
                (cdn ? ossFConfig.getCdnDomain() : ossFConfig.getCanonicalDomain()));
        return finalUrl;
    }

    public void delete(File file) {
        if (file == null) {
            return;
        }
        try {
            file.delete();
        } catch (Exception e) {
            L.error(e);
        }
    }

    public String md5(File file) throws IOException {
        InputStream in = new FileInputStream(file);
        try {
            return DigestUtils.md5Hex(in);
        } finally {
            FileUtil.close(in);
        }
    }

    public File createTmpFile(String namespace, String extension) throws Exception {
        File dir = new File("/tmp/ccpc-api", namespace);
        dir.mkdirs();
        String fileName = TMP_FILE_ID_CREATOR.create();
        if (extension != null) {
            fileName += "." + extension;
        }
        File file = new File(dir, fileName);
        return file;
    }

}
