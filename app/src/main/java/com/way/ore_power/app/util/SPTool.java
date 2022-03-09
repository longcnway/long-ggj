package com.way.ore_power.app.util;


import android.content.Context;
import android.content.SharedPreferences;


/**
 * sharedPreference 工具类
 * Created by b508a on 2015/12/31.
 */
public class SPTool {
    private static String spFileName = "jifubaosp";

    /**
     * 获取首选项
     *
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences(spFileName, Context.MODE_PRIVATE);
    }

    /**
     * 向首选项中存取数据(仅限于String,Integer,Boolean)
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, Object value) {
        SharedPreferences sp = getSharedPreference(context);
        SharedPreferences.Editor editor = sp.edit();
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        }
        editor.apply();
    }

    /**
     * 获取String
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getString(key, null);
    }

    /**
     * 获取String
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key, String def) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getString(key, def);
    }

    /**
     * 获取int
     *
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, 0);
    }

    /**
     * 获取int(可以控制默认值)
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getInt(key, defaultValue);
    }

    /**
     * 获取float
     *
     * @param context
     * @param key
     * @return
     */
    public static float getFloat(Context context, String key) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getFloat(key, 0);
    }

    /**
     * 获取float(可以控制默认值)
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static float getFloat(Context context, String key, int defaultValue) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getFloat(key, defaultValue);
    }

    /**
     * 获取long
     *
     * @param context
     * @param key
     * @return
     */
    public static long getLong(Context context, String key) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getLong(key, 0);
    }

    /**
     * 获取long(可以控制默认值)
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(Context context, String key, int defaultValue) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getLong(key, defaultValue);
    }

    /**
     * 获取Boolean
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * 获取Boolean
     *
     * @param context
     * @param key
     * @param defaultValue 默认值
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = getSharedPreference(context);
        return sp.getBoolean(key, defaultValue);
    }


    /**
     * 移除对应key的值
     *
     * @param context
     * @param key
     */
    public static void removeKeyValue(Context context, String key) {
        SharedPreferences preferences = getSharedPreference(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key);//需要删除存储内容只需调用remove(),移除对应的key
        editor.apply();
    }

    /**
     * 清除SharedPreferences文件的所有数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences preferences = getSharedPreference(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}

