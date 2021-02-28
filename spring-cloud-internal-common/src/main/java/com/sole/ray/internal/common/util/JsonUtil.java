package com.sole.ray.internal.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

    private static ObjectMapper MAPPER = new ObjectMapper();

    public static  <T> T convert(Object object, Class<T> resultType){
        return MAPPER.convertValue(object,resultType);
    }
}
