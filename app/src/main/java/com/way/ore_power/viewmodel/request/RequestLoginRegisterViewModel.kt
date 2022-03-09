package com.way.ore_power.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.way.ore_power.data.model.bean.UserInfo
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.state.ResultState

class RequestLoginRegisterViewModel : BaseViewModel() {

    var loginResult = MutableLiveData<ResultState<UserInfo>>()
    var forgetPasswordResult = MutableLiveData<ResultState<Any>>()

//    fun loginReq(vararg value: String) {
//        //1.这种是在 Activity/Fragment的监听回调中拿到已脱壳的数据（项目有基类的可以用）
//        val message = ReqMessage.Builder()
//            .addParam("mobile", value[0])
//            .addParam("password", value[1])
//            .build()
//        request(
//            { HttpRequestManager.apiService.login(message.getRequestString()) }//请求体
//            , loginResult,//请求的返回结果，请求成功与否都会改变该值，在Activity或fragment中监听回调结果，具体可看loginActivity中的回调
//            true,//是否显示等待框，，默认false不显示 可以默认不传
//            "正在登录中..."//等待框内容，可以默认不填请求网络中...
//        )
//
//    }
//
//    fun registerReq(vararg value: String) {
//        val message = ReqMessage.Builder()
//            .addParam("mobile", value[0])
//            .addParam("password", value[1])
//            .addParam("code", value[2])
//            .addParam("openid", value[3])
//            .build()
//        request(
//            { HttpRequestManager.apiService.register(message.getRequestString()) }
//            , loginResult,
//            true,
//            "正在注册中..."
//        )
//    }
//
    fun forgetPassword(mobile: String, password: String, code: String) {
//        val message = ReqMessage.Builder()
//            .addParam("mobile", mobile)
//            .addParam("password", password)
//            .addParam("code", code)
//            .build()
//        request(
//            { HttpRequestManager.apiService.forgetPassword(message.getRequestString()) }
//            , forgetPasswordResult,
//            true,
//            "正在请求中..."
//        )
    }


}
