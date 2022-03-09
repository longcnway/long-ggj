package com.way.ore_power.viewmodel.state

import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.callback.databind.BooleanObservableField
import me.hgj.jetpackmvvm.callback.databind.StringObservableField
import me.hgj.jetpackmvvm.callback.livedata.StringLiveData

class LoginRegisterViewModel:BaseViewModel() {

    //用户名
    var username = StringLiveData()

    //密码(登录注册界面)
    var password = StringObservableField()

    //验证码
    var code = StringObservableField()

    var password2 = StringObservableField()

    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanObservableField()

    var isShowPwd2 = BooleanObservableField()

}
