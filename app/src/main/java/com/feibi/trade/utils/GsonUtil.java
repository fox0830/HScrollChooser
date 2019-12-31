package com.feibi.trade.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import jh.app.android.basiclibrary.entity.BasicResponseBody;

public class GsonUtil {
    public static BasicResponseBody fromJson(String json, Class clazz) throws Exception {
        Gson gson = new Gson();
        Type objectType = type(BasicResponseBody.class, clazz);
        return gson.fromJson(json, objectType);
    }

    static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
