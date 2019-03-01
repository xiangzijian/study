package com.xxx.util;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CommUtil {
    /**
     * map 转对象
     */
    public static <T> T mapToObject(Map map, Class<T> clazz) {
        try {
            T t = clazz.newInstance();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name=field.getType().getSimpleName();
                setToObject(t,name,field,map.get(field.getName()));
            }
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**将值解析放入对象中*/
    public static <T> void  setToObject(T t,String str,Field field,Object o) throws IllegalAccessException {
        switch (str){
            case "String": field.set(t, String.valueOf(o));return;
            case "Long": field.set(t,Long.valueOf(String.valueOf(o)));return;
            case "Date":field.set(t,new Date(Date.parse(String.valueOf(o))));return;
            case "Integer":field.set(t,Integer.valueOf(String.valueOf(o)));return;
            case "boolean" :field.set(t,Boolean.valueOf(String.valueOf(o)));return;
            case "long": field.set(t,Long.valueOf(String.valueOf(o)));return;
            case "int": field.set(t,Integer.valueOf(String.valueOf(o)));return;
            default:break;
        }

    }

    /**
     * 对象转map
     */
    public static Map objectToMap(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        Map map = new HashMap();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(),String.valueOf(field.get(obj)) );
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

}
