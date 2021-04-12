package com.ali.leb.hbase.util;

import com.ali.leb.hbase.annotation.HbaseClipping;
import com.ali.leb.hbase.base.HbaseModel;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.BooleanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ObjectReflect {
    public static final char LOWER_A = 'a';
    public static final char LOWER_Z = 'z';

    private static final Map<String, Boolean> map = new HashMap<>();

    static {
        map.put("byte", true);
        map.put("short", true);
        map.put("int", true);
        map.put("long", true);
        map.put("float", true);
        map.put("double", true);
        map.put("char", true);
        map.put("boolean", true);
        map.put("Integer", true);
        map.put("java.lang.Byte", true);
        map.put("java.lang.Short", true);
        map.put("java.lang.Integer", true);
        map.put("java.lang.Long", true);
        map.put("java.lang.Float", true);
        map.put("java.lang.Double", true);
        map.put("java.lang.String", true);
        map.put("java.lang.Boolean", true);
    }

    /**
     * 对外方法
     *
     * @param object
     * @return
     */
    public static JSONObject getObject(Object object) {
        return getClass(object);
    }

    /**
     * 获取json对象信息
     *
     * @param object
     * @return
     */
    public static JSONObject getClass(Object object) {
        if (object == null) {
            throw new IllegalArgumentException("object is null");
        }
        JSONObject jsonObject = new JSONObject();
        try {
            //获取类的所有属性
            Class<?> clazz = object.getClass();
            Class<?> superclass = clazz.getSuperclass();
            Object superObject = superclass.newInstance();
            if (!(superObject instanceof HbaseModel)) {
                return jsonObject;
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                //允许访问私有属性
                field.setAccessible(true);
                //忽略的属性
                HbaseClipping clipping = field.getAnnotation(HbaseClipping.class);
                if (clipping != null) {
                    continue;
                }
                //属性名
                String name = field.getName();
                Object value = field.get(object);
                Type genericType = field.getGenericType();
                String typeName = genericType.getTypeName();
                if (BooleanUtils.isTrue(map.get(typeName))) {
                    putNameAndValue(jsonObject, name, value);
                    continue;
                }
                //循环迭代类属性
                repeatObject(jsonObject, typeName, name, clazz, object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /**
     * 判断时候要循环调用
     *
     * @param jsonObject
     * @param typeName
     * @param name
     * @param clazz
     * @param object
     */
    public static void repeatObject(JSONObject jsonObject, String typeName, String name, Class<?> clazz, Object object) {
        try {
            Class<?> aClass = Class.forName(typeName).getSuperclass();
            Object o = aClass.newInstance();
            if (o instanceof HbaseModel) {
                String getObject = toGetMethod(name);
                Method method = clazz.getMethod(getObject);
                Object invoke = method.invoke(object);
                if (invoke != null) {
                    putNameAndValue(jsonObject, name, getClass(invoke));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把值存入json对象中
     *
     * @param jsonObject
     * @param name
     * @param value
     */
    public static void putNameAndValue(JSONObject jsonObject, String name, Object value) {
        if (value != null) {
            jsonObject.put(name, value);
        }
    }

    /**
     * 拼接get方法，目的是获取对象信息
     *
     * @param name
     * @return
     */
    public static String toGetMethod(String name) {
        char[] chars = name.toCharArray();
        if (chars[0] >= LOWER_A && chars[0] <= LOWER_Z) {
            chars[0] = (char) (chars[0] - 32);
        }
        return "get" + new String(chars);
    }
}
