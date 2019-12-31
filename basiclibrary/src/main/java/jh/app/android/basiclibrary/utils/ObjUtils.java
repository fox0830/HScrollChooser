package jh.app.android.basiclibrary.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

/**
 * 工具类（数据类型换等）
 * Created by baorui on 2017/4/28.
 */

public class ObjUtils {

    /**
     * 将json字符串转换为map
     * @param jsonString json字符串
     * @return HashMap
     */
    public static HashMap<String, Object> jsonToMap (String jsonString) {
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        HashMap<String, Object> map;
        try {
            map = g.fromJson(jsonString, new TypeToken<HashMap<String, Object>>() {}.getType());
        } catch (Exception e) {
            map = null;
        }
        return map;
    }

    /**
     * 将json字符串转化为对象
     * @param jsonString json字符串
     * @param classOfT 转换的目标对象类
     * @return T 目标对象
     */
    public static <T>T jsonToObject(String jsonString, Class<T> classOfT){
        T reObj;
        try {
            reObj = new Gson().fromJson(jsonString, classOfT);
        } catch (Exception e) {
            reObj = null;
        }
        return reObj;
    }

}
