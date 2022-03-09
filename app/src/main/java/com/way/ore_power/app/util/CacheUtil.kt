package com.way.ore_power.app.util

import android.text.TextUtils
import com.google.gson.Gson
import com.way.ore_power.data.model.bean.UserInfo

object CacheUtil {
    /**
     * 获取保存的账户信息
     */
    fun getUser(): UserInfo? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        val userStr = kv.decodeString("user")
        val userStr=SPTool.getString(AppUtil.getContext(), "user")
        return if (TextUtils.isEmpty(userStr)) {
            null
        } else {
            Gson().fromJson(userStr, UserInfo::class.java)
        }
    }

    /**
     * 设置账户信息
     */
    fun setUser(userResponse: UserInfo?) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
        if (userResponse == null) {
//            kv.encode("user", "")
            SPTool.put(AppUtil.getContext(), "user", "")
            setIsLogin(false)
        } else {
//            kv.encode("user", Gson().toJson(userResponse))
            SPTool.put(AppUtil.getContext(), "user", Gson().toJson(userResponse))
            setIsLogin(true)
        }

    }

    /**
     * 是否已经登录
     */
    fun isLogin(): Boolean {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        return kv.decodeBool("login", false)
        return SPTool.getBoolean(AppUtil.getContext(), "login", false)

    }

    /**
     * 设置是否已经登录
     */
    fun setIsLogin(isLogin: Boolean) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("login", isLogin)
        SPTool.put(AppUtil.getContext(), "login", isLogin)

    }

    /**
     * 是否是第一次登陆
     */
//    fun isFirst(): Boolean {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        return kv.decodeBool("first", true)
//    }

    /**
     * 是否是第一次登陆
     */
//    fun setFirst(first: Boolean): Boolean {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        return kv.encode("first", first)
//    }

    /**
     * 首页是否开启获取指定文章
     */
//    fun isNeedTop(): Boolean {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        return kv.decodeBool("top", true)
//    }

    /**
     * 设置首页是否开启获取指定文章
     */
//    fun setIsNeedTop(isNeedTop: Boolean): Boolean {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        return kv.encode("top", isNeedTop)
//    }

    /**
     * 获取搜索历史缓存数据
     */
//    fun getSearchHistoryData(): ArrayList<String> {
//        val kv = MMKV.mmkvWithID("cache")
//        val searchCacheStr = kv.decodeString("history")
//        if (!TextUtils.isEmpty(searchCacheStr)) {
//            return Gson().fromJson(searchCacheStr
//                , object : TypeToken<ArrayList<String>>() {}.type
//            )
//        }
//        return arrayListOf()
//    }

    fun setSearchHistoryData(searchResponseStr: String) {
//        val kv = MMKV.mmkvWithID("cache")
//        kv.encode("history", searchResponseStr)
    }

    /**
     * 获取Mac地址
     */
    fun gettMac(): String? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        val mac = kv.decodeString("mac")
        val mac = SPTool.getString(AppUtil.getContext(), "mac", "")
        return if (TextUtils.isEmpty(mac)) {
            null
        } else {
            mac
        }
    }

    fun setMac(mac: String?) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("mac", mac)
        SPTool.put(AppUtil.getContext(), "mac", mac)

    }

    /**
     * 保存token
     */
    fun setToken(token: String?) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("token", token)
        SPTool.put(AppUtil.getContext(), "token", token)
    }

    /**
     * 获取token
     */
    fun getToken(): String? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        val token = kv.decodeString("token")
        val token = SPTool.getString(AppUtil.getContext(), "token", "")
        return if (TextUtils.isEmpty(token)) {
            null
        } else {
            token
        }
    }

    /**
     * 设置微信授权码
     */
    fun setWeiChatCode(token: String?) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("weichat_code", token)
        SPTool.put(AppUtil.getContext(), "weichat_code", token)

    }

    /**
     * 获取微信授权码
     */
    fun getWeiChatCode(): String? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        val token = kv.decodeString("weichat_code")
        val token = SPTool.getString(AppUtil.getContext(), "weichat_code", "")
        return if (TextUtils.isEmpty(token)) {
            null
        } else {
            token
        }
    }

    fun setLatitude(latitude: Double) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("latitude", latitude.toString())
        SPTool.put(AppUtil.getContext(), "latitude", latitude)
    }

    fun getLatitude(): String? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
        var latitude = SPTool.getString(AppUtil.getContext(), "latitude", "")
        return if (TextUtils.isEmpty(latitude)) {
            ""
        } else {
            latitude
        }
    }

    fun setLongitude(longitude: Double) {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("longitude", longitude.toString())
        SPTool.put(AppUtil.getContext(), "longitude", longitude)
    }

    fun getLongitude(): String? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        val longitude = kv.decodeString("longitude")
        val longitude = SPTool.getString(AppUtil.getContext(), "longitude", "")
        return if (TextUtils.isEmpty(longitude)) {
            null
        } else {
            longitude
        }
    }

    fun setLoginCity(city: String) {
        SPTool.put(AppUtil.getContext(), "city", city)
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        kv.encode("city", city)
    }

    fun getLoginCity(): String? {
//        val kv = MMKV.mmkvWithID("jf_oyyf")
//        val city = kv.decodeString("city")
        val city = SPTool.getString(AppUtil.getContext(), "city", "")
        return if (TextUtils.isEmpty(city)) {
            null
        } else {
            city
        }
    }
}