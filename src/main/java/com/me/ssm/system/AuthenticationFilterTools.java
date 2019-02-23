package com.me.ssm.system;

import java.util.regex.Pattern;

/**
 * 用于总体过滤的工具类
 * @author 不语恋
 */
public class AuthenticationFilterTools {

    private static final Pattern PATTERN=Pattern.compile("[0-9]+");

    /** 匹配前缀数组 */
    public static boolean matchPrefix(String url, String prefixes[]) {
        for (String prefixe : prefixes) {
            if (url.startsWith(prefixe)) {
                return true;
            }
        }
        return false;
    }

    /** 匹配后缀数组 */
    public static boolean matchsuffix(String url, String suffixes[]) {
        for (String suffixe : suffixes) {
            if (url.endsWith(suffixe)) {
                return true;
            }
        }
        return false;
    }

    /** 判断字符串是否为数组 */
    public static boolean isNumeric(String str) {
        return PATTERN.matcher(str).matches();
    }
}
