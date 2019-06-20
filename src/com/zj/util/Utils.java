package com.zj.util;


import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实用工具类 Created by jackytsu on 2017/3/14.
 */
public class Utils {
    /**
     * 获取字符串的MD5值
     *
     * @param str
     * @return
     */
    public static String getMD5String(Object str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            if (str instanceof String) {
                md.update(((String) str).getBytes("UTF-8"));
            } else if (str instanceof char[]) {
                md.update(String.valueOf(((char[]) str)).getBytes("UTF-8"));
            }

            StringBuffer buf = new StringBuffer();
            for (byte b : md.digest()) {
                buf.append(String.format("%02X", b & 0xff));
            }
            return buf.toString();
        } catch (Exception e) {
            e.printStackTrace();

            return "";
        }
    }

    /**
     * 判断对象是否为空
     *
     * @param obj
     * @return
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || str.trim().length() == 0;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return isNull(collection) || collection.size() == 0;
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return isNull(array) || array.length == 0;
    }


    /**
     * 格式化输出
     *
     * @param format
     * @param values
     * @return
     */
    public static String sout(String format, Object... values) {
        return String.format(format, values);
    }

    /**
     * 正则匹配截取
     * @param regex
     * @param source
     * @return
     */
    public static String getMatcher(String source, String regex) {
        String result = "";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(1);
        }
        return result;
    }

    /**
     * 获取当前时间
     * @return
     */
    public static Date  getCurrTime(){
        Calendar ca = Calendar.getInstance();
        return ca.getTime();
    }

    /**
     * 获取当前整点时间
     * @return
     */
    public static Date  getCurrHourTime(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        return ca.getTime();
    }

    /**
     * 获取当前整点时间
     * @return
     */
    public static String  getCurrHourTimeString(){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(ca.getTime());

    }

    /**
     * 获取后几小时的整点时间
     * @return
     */
    public static String  getFutureHourTime(int n){
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.add(Calendar.HOUR,n);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(ca.getTime());
    }


    /**
     * 格式化double，保留小数
     * @param value
     * @param formater
     * @return
     */
    public static String doubleFormat(Double value, String formater){
        return new DecimalFormat(formater).format(value);
    }
    /**
     * 格式化double，保留两位小数
     * @param value
     * @return
     */
    public static String doubleFormat(Double value){
        return new DecimalFormat("#.00").format(value);
    }

    /**
     * 格式化时间
     * @param date
     * @return
     */
    public static String dataFormate(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return  format.format(date);
    }

    /**
     * 格式化时间
     * @param date
     * @param pattern
     * @return
     */
    public static String dataFormate(Date date,String pattern){
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return  format.format(date);
    }

    public static <T> void pubSubMap(Map<String,Map<String,T>> map,String key,String subKey,T obj){
        Map<String,T> subMap = map.get(key);
        if(subMap == null){
            subMap=new HashMap<String, T>();
            map.put(key, subMap);
        }
        subMap.put(subKey,obj);
    }
}
