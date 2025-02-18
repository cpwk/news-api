package com.maidao.edu.news.common.file.service;

import com.aliyun.oss.OSSClient;
import com.maidao.edu.news.common.exception.ServiceException;
import com.maidao.edu.news.common.file.entity.OSSConfig;
import com.maidao.edu.news.common.util.StringUtils;
import com.sunnysuperman.commons.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;

import static com.maidao.edu.news.common.exception.ErrorCode.ERR_FILE_NONE;
import static com.maidao.edu.news.common.exception.ErrorCode.ERR_FILE_THAN;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:OssServiceImpl
 * 类描述:服务层实现类
 **/
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String upload(MultipartFile file) throws Exception {
        OSSClient ossClient = new OSSClient(ossConfig.getEndpoint(), ossConfig.getKey(), ossConfig.getSecret());
        if (file == null) {
            throw new ServiceException(ERR_FILE_NONE);
        }
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new ServiceException(ERR_FILE_THAN);
        }

        File tmpFile = createTmpFile("pictures", FileUtil.getFileExt(file.getOriginalFilename()));    //getFileExt（获得文件后缀名）
        FileUtil.copy(file.getInputStream(), new FileOutputStream(tmpFile));

        String fileName = tmpFile.getName();

        String namespace = "image";

        ossClient.putObject(ossConfig.getBucket(), namespace + "/" + fileName, tmpFile);

        String ret = "http://" + ossConfig.getBucket() + "." + ossConfig.getEndpoint() + "/" + namespace + "/" + fileName;

        return ret;

    }

    public File createTmpFile(String namespace, String extension) throws Exception {
        File dir = new File("/data", namespace);
        dir.mkdirs();
        String fileName = StringUtils.getUUID();
        if (extension != null) {
            fileName += "." + extension;
        }

        return new File(dir, fileName);
    }
}
