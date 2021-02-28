package com.sole.ray.internal.common.enhancer;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Slf4j
public class FieldEnhancer {

    public static Object getObject(Object bean, Map<String, Object> newFieldValueMap) throws InvocationTargetException, IllegalAccessException {


        //1.根据新属性的值，获取属性类型
        Map<String, Class> newFiledTypeMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : newFieldValueMap.entrySet()) {
            if (entry.getValue() == null) {
                throw new NullPointerException();
            }
            newFiledTypeMap.put(entry.getKey(),entry.getValue().getClass());
        }

        //2.获取原对象的字段类型map和字段值map
        Class<?> targetClass = bean.getClass();
        Map<String, Class> oldFiledTypeMap = new HashMap<>();
        Map<String, Object> oldFiledValueMap = new HashMap<>();

        Stream.of(targetClass.getDeclaredFields()).forEach(field -> {
            field.setAccessible(true);
            oldFiledTypeMap.put(field.getName(),field.getType());
            try {
                oldFiledValueMap.put(field.getName(),field.get(bean));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });


        //3.将两个map合并
        newFiledTypeMap.putAll(oldFiledTypeMap);
        newFieldValueMap.putAll(oldFiledValueMap);

        //4.根据新的字段组合生成子类对象
        DynamicBean dynamicBean = new DynamicBean(bean.getClass(), newFiledTypeMap);

        //5.放回合并后的属性集合
        newFieldValueMap.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
                log.error("动态添加字段【值】出错", e);
            }
        });
        return dynamicBean.getTarget();
    }
}