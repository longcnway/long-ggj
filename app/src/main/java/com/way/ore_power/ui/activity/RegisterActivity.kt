package com.way.ore_power.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.way.ore_power.R
import com.way.ore_power.app.base.BaseActivity
import com.way.ore_power.app.ext.showMessage
import com.way.ore_power.databinding.ActivityRegisterBinding
import com.way.ore_power.viewmodel.request.RequestLoginRegisterViewModel
import com.way.ore_power.viewmodel.state.LoginRegisterViewModel
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.StatusBarUtil

class RegisterActivity : BaseActivity<LoginRegisterViewModel, ActivityRegisterBinding>() {

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun layoutId() = R.layout.activity_register

    override fun initIntent() {
        val bundle = intent.extras
    }


    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        mDatabind.user = mViewModel
    }

    inner class ProxyClick {

        //发送验证码
        fun sendCode() {

        }

        //下一步（注册提交）
        fun nextStep() {
            when {
                mViewModel.username.value.isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                mViewModel.code.get().isEmpty() -> showMessage("请验证码")
                else -> {
//                    requestLoginRegisterViewModel.registerReq(
//                        mViewModel.username.value,
//                        mViewModel.password.get(),
//                        mViewModel.code.get(),
//                        openId
//                    )
                }
            }
        }
    }

    override fun createObserver() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0)
        requestLoginRegisterViewModel.loginResult.observe(this, Observer { resultState ->
            parseState(resultState, {
                //登录成功 通知账户数据发生改变鸟
//                CacheUtil.setUser(it)
//                CacheUtil.setIsLogin(true)
//                CacheUtil.setToken(it.appToken)
//                shareViewModel.userinfo.postValue(it)

            }, {
                //注册失败
                showMessage(it.errorMsg)
            })
        })
    }

}