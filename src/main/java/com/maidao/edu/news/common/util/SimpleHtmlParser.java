package com.maidao.edu.news.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:SimpleHtmlParser
 * 类描述:TODO
 **/
public class SimpleHtmlParser {

    private static String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
    private static String regEx_copyright = "<p data-f-id=[^>]*?>.*?</p>"; // 定义script的正则表达式

    // 过滤script标签
    public static String removeScript(String html) {
        if (StringUtils.isEmpty(html)) {
            return "";
        }
        html = removeTag(html, regEx_copyright);
        return removeTag(html, regEx_script);
    }

    private static String removeTag(String html, String reg) {
        Pattern p_script = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(html);
        html = m_script.replaceAll("");
        return html.trim();
    }

}
