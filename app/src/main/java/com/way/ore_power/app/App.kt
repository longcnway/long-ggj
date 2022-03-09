package com.way.ore_power.app

import androidx.multidex.MultiDex
import com.way.ore_power.app.widght.loadCallBack.LoadingCallback
import com.way.ore_power.app.widght.loadCallBack.ErrorCallback
import com.way.ore_power.app.widght.loadCallBack.EmptyCallback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadSir
import me.hgj.jetpackmvvm.base.BaseApp

class App :BaseApp(){

    companion object{
        lateinit var instance:App
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        MultiDex.install(this)
        //界面加载管理 初始化
        LoadSir.beginBuilder()
            .addCallback(LoadingCallback())//加载
            .addCallback(ErrorCallback())//错误
            .addCallback(EmptyCallback())//空
            .setDefaultCallback(SuccessCallback::class.java)//设置默认加载状态页
            .commit()
    }

}
