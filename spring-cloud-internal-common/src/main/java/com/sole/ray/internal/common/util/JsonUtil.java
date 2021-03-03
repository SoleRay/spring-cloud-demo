package com.sole.ray.internal.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 功能：Map -> Java对象
     */
    public static  <T> T convert(Object object, Class<T> resultType){
        return MAPPER.convertValue(object,resultType);
    }

    /**
     * 功能：json -> Java对象
     */
    public static <T> T convertJsonToJavaObject(String jsonStr, Class<T> resultType) throws JsonProcessingException {
        return MAPPER.readValue(jsonStr,resultType);
    }
}
