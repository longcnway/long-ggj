package com.way.ore_power.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.way.ore_power.R
import com.way.ore_power.app.base.BaseActivity
import com.way.ore_power.app.ext.showMessage
import com.way.ore_power.databinding.ActivityForgetPasswordBinding
import com.way.ore_power.viewmodel.request.RequestLoginRegisterViewModel
import com.way.ore_power.viewmodel.state.LoginRegisterViewModel
import kotlinx.android.synthetic.main.include_toolbar.*
import me.hgj.jetpackmvvm.ext.parseState
import me.hgj.jetpackmvvm.util.StatusBarUtil

class ForgetPasswordActivity : BaseActivity<LoginRegisterViewModel, ActivityForgetPasswordBinding>() {

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun layoutId() = R.layout.activity_forget_password

    override fun initBar() {

    }
    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        mDatabind.user = mViewModel
    }

    inner class ProxyClick {
        //发送验证码
        fun sendCode() {

        }

        //重置密码
        fun resetPassword() {
            when {
                mViewModel.username.value.isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
                mViewModel.code.get().isEmpty() -> showMessage("请验证码")
                else -> requestLoginRegisterViewModel.forgetPassword(
                    mViewModel.username.value,
                    mViewModel.password.get(),
                    mViewModel.code.get()
                )
            }
        }
    }

    override fun createObserver() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this,0)
        requestLoginRegisterViewModel.forgetPasswordResult.observe(this, Observer { resultState ->
            parseState(resultState, {
                //重置密码成功
                launchActivity(LoginActivity::class.java)
            }, {
                //重置密码失败
                showMessage(it.errorMsg)
            })
        })
    }
}