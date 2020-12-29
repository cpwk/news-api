package com.maidao.edu.news.springbootdemo.common.file.service;


import com.maidao.edu.news.springbootdemo.common.util.Base64Utils;
import com.maidao.edu.news.springbootdemo.common.util.L;
import com.maidao.edu.news.springbootdemo.common.util.SimpleHttpClient;
import com.sunnysuperman.commons.util.StringUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:ImgBase64Utils
 * 类描述:图片上传服务类
 **/
public class ImgBase64Utils {

    public static String base64FromURL(String url) {
        SimpleHttpClient client = new SimpleHttpClient();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        SimpleHttpClient.DownloadOptions options = new SimpleHttpClient.DownloadOptions().setMaxSize(3 * 1024 * 1024).setRetrieveResponseHeaders(true);
        boolean downloaded = false;
        try {
            downloaded = client.download(url, baos, options);
        } catch (IOException ex) {
            // ignore
        }
        String result;
        if (downloaded) {
            try {
                baos.flush();
            } catch (IOException e) {
                L.error(e);
            }
            byte[] bytes = baos.toByteArray();
            String contentType = options.getResponseHeaders().get("content-type");
            if (contentType == null) {
                contentType = "image/jpg";
            }
            String prefix = "data:" + contentType + ";base64,";
            String imgAsBase64 = Base64Utils.encode(bytes);
            StringBuilder buf = new StringBuilder(prefix.length() + imgAsBase64.length());
            buf.append(prefix).append(imgAsBase64);
            result = buf.toString();
        } else {
            result = StringUtil.EMPTY;
        }
        return result;
    }

}