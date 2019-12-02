package com.zj.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReflectUtil {
    /**
     * 根据传入的class和参数，实例化对象</P>
     * 现只支持属性为String ，Interger，Double,Date类型的字段
     * @param clazz
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T encpsulation(Class<T> clazz,Map<String,String> params){
        Object obj=null;
        try {
            obj=clazz.newInstance();
            setFileValue(obj,params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

    // 把一个字符串的第一个字母大写
    private static String getMethodName(String fildeName) throws Exception{
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 使用反射，给对象的某个属性赋值</P>
     * 现只支持属性为String ，Interger，Double,Date类型的字段
     * @param obj
     * @param field
     * @param value
     */
    public static void setFileValue(Object obj, Field field, String value){
        try {
            String fieldType = field.getGenericType().toString();

            if("class java.lang.Integer".equals(fieldType) || "int".equals(fieldType)){
                Double dValue = Double.parseDouble(value);//处理excel读取数字，带".0"
                field.set(obj,dValue.intValue());
            }
            else if("class java.lang.Double".equals(fieldType) || "double".equals(fieldType) ){
                field.set(obj,Double.parseDouble(value));
            }
            else if("class java.lang.Long".equals(fieldType) || "long".equals(fieldType) ){
                Double dValue = Double.parseDouble(value);//处理excel读取数字，带".0"
                field.set(obj,dValue.longValue());
            }
            else if("class java.lang.String".equals(fieldType)){
                field.set(obj,value);
            }else if("class java.util.Date".equals(fieldType)){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                field.set(obj,format.parse(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用反射，给对象的某个属性赋值</P>
     * 现只支持属性为String ，Interger，Double,Date类型的字段
     * @param obj
     * @param fieldName
     * @param value
     */
    public static void setFileValue(Object obj, String  fieldName, String value){
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            setFileValue(obj,field,value);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }



    /**
     * 使用反射，给对象的属性赋值</P>
     * 现只支持属性为String ，Interger，Double,Date类型的字段
     * @param obj
     * @param params Map<fieldName,fieldValue>
     */
    public static void setFileValue(Object obj,Map<String,String> params){
        try {
            Set<String> keySet= params.keySet();
            for (String key: keySet) {
                Field field = obj.getClass().getDeclaredField(key);
                field.setAccessible(true);
                setFileValue(obj,field,params.get(key));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 使用反射，获取对象的属性赋值</P>
     * @param obj
     * @param FileName
     */
    public static Object getFileValue(Object obj,String FileName){
        try {
            Field field = obj.getClass().getField(FileName);
            field.setAccessible(true);
            Object o = field.get(obj);
            return o;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        HashMap<String, String> params = new HashMap<>();
        params.put("temperature","20.9");
        params.put("monitoringId","2012031032");
        params.put("monitoringName","城标要桂林夺要");
        params.put("monitoringIndex","12");
        params.put("distance","12.0");
        Calendar instance = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        params.put("monitoringTime",format.format(instance.getTime()));
        //FiberTemperature encpsulation = encpsulation(FiberTemperature.class, params);
        //System.out.println(encpsulation.toString());
    }
}
