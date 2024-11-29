package com.maidao.edu.news.common.file.entity;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:UploadOptions
 * 类描述:上传选项类
 **/

public class UploadOptions {
    private String contentType;
    private AccessPermission permission;
    private String namespace;
    private int randomLength;
    private String fileName;

    public String getContentType() {
        return contentType;
    }

    public UploadOptions setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public AccessPermission getPermission() {
        return permission;
    }

    public UploadOptions setPermission(AccessPermission permission) {
        this.permission = permission;
        return this;
    }

    public String getNamespace() {
        return namespace;
    }

    public UploadOptions setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public int getRandomLength() {
        return randomLength;
    }

    public UploadOptions setRandomLength(int randomLength) {
        this.randomLength = randomLength;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public UploadOptions setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public enum AccessPermission {
        DEFAULT, PRIVATE, PUBLIC_READ
    }
}