package jh.app.android.basiclibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Used 临时存储数据操作类（全）
 */
public class PreferencesUtils {
    public static String PREFERENCE_NAME = "honeywell";

    public static boolean contains(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.contains(key);
    }

    /**
     * 存储字符串
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * 读取字符串
     */
    public static String getString(Context context, String key) {
        return getString(context, key, "");
    }

    /**
     * 读取字符串（带默认值的）
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getString(key, defaultValue);
    }

    /**
     * 存储整型数字
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * 读取整型数字
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * 读取整型数字（带默认值的）
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getInt(key, defaultValue);
    }

    /**
     * 存储长整型数字
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * 读取长整型数字
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, 0xffffffff);
    }

    /**
     * 读取长整型数字（带默认值的）
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getLong(key, defaultValue);
    }

    /**
     * 存储Float数字
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * 读取Float数字
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1.0f);
    }

    /**
     * 读取Float数字（带默认值的）
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getFloat(key, defaultValue);
    }

    /**
     * 存储boolean类型数据
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * 读取boolean类型数据
     * 默认值false
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 读取boolean类型数据（带默认值的）
     * 默认值true
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCE_NAME, 0);
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * 修改指定数据
     * String类型
     */
    public static boolean ModifyPreferences(Context context, String Key, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(Key, value);
        return editor.commit();
    }

    /**
     * 修改指定数据
     * int类型
     */
    public static boolean ModifyPreferences(Context context, String Key, int value) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(Key, value);
        return editor.commit();
    }

    /**
     * 修改指定数据
     * float类型
     */
    public static boolean ModifyPreferences(Context context, String Key, float value) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putFloat(Key, value);
        return editor.commit();
    }

    /**
     * 修改指定数据
     * long类型
     */
    public static boolean ModifyPreferences(Context context, String Key, long value) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(Key, value);
        return editor.commit();
    }

    /**
     * 修改指定数据
     * long类型
     */
    public static boolean ModifyPreferences(Context context, String Key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(Key, value);
        return editor.commit();
    }

    /**
     * 清除数据
     */
    public static boolean clearPreferences(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        return editor.commit();
    }

    /**
     * 清除指定数据
     */
    public static boolean removePreferences(Context context, String Key) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(Key);
        return editor.commit();
    }
}