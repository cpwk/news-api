package com.maidao.edu.news.common.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:OssService
 * 类描述:服务层接口
 **/
public interface OssService {
    String upload(MultipartFile file) throws Exception;
}
