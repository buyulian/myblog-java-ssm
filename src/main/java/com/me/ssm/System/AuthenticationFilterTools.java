package com.me.ssm.System;

import java.util.regex.Pattern;

/**
 * Created by 不语恋 on 2017/5/11.
 */
//用于总体过滤的工具类
public class AuthenticationFilterTools {
    //匹配前缀数组
    public static boolean matchPrefix(String url,String prefixes[]){
        for(int i=0;i<prefixes.length;i++)
            if(url.startsWith(prefixes[i]))
                return true;
        return false;
    }
    //匹配后缀数组
    public static boolean matchsuffix(String url,String suffixes[]){
        for(int i=0;i<suffixes.length;i++)
            if(url.endsWith(suffixes[i]))
                return true;
        return false;
    }
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(str).matches();
    }
}
