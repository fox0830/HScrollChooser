package com.feibi.cinch.utils;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import jh.app.android.basiclibrary.entity.BasicResponseBody;

public class GsonUtil {
    public static Object fromJson(String json, Class clazz) throws Exception {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
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
