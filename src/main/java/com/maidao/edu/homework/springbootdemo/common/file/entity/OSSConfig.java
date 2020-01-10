package com.maidao.edu.homework.springbootdemo.common.file.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:OSSConfig
 * 类描述:个人阿里云oss服务相关配置
 **/
@ConfigurationProperties(prefix = "oss", ignoreUnknownFields = false)
@Component
@Validated
public class OSSConfig {

    @NotNull
    private String endpoint;
    @NotNull
    private String bucket;
    @NotNull
    private String namespace;
    @NotNull
    private String key;
    @NotNull
    private String secret;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
