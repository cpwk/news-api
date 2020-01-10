package com.maidao.edu.homework.springbootdemo.common.util;

import com.sunnysuperman.commons.util.FileUtil;

import java.io.IOException;
import java.io.InputStream;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:R
 * 类描述:TODO
 **/
public class R {

    public static InputStream getStream(String path) {
        return R.class.getResourceAsStream("/" + path);
    }

    public static String getString(String path) {
        try {
            return FileUtil.read(getStream(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static byte[] getBytes(String path) {
        try {
            return FileUtil.readAsByteArray(getStream(path));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}