package com.way.ore_power.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.blankj.utilcode.util.ToastUtils
import com.way.ore_power.R
import com.way.ore_power.app.base.BaseActivity
import com.way.ore_power.app.ext.showMessage
import com.way.ore_power.databinding.ActivityLoginBinding
import com.way.ore_power.viewmodel.request.RequestLoginRegisterViewModel
import com.way.ore_power.viewmodel.state.LoginRegisterViewModel
import me.hgj.jetpackmvvm.util.StatusBarUtil

class LoginActivity:BaseActivity<LoginRegisterViewModel,ActivityLoginBinding>() {

    private val requestLoginRegisterViewModel: RequestLoginRegisterViewModel by viewModels()

    override fun layoutId() = R.layout.activity_login


    var exitTime = 0L

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        mDatabind.user = mViewModel

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - exitTime > 2000) {
                    ToastUtils.showShort("再按一次退出程序")
                    exitTime = System.currentTimeMillis()
                } else {
                    finish()
                }
            }
        })
    }

    override fun createObserver() {
        StatusBarUtil.setTranslucentForCoordinatorLayout(this, 0)

    }

    inner class ProxyClick {
        //注册
        fun toRegister() {
            var bundle = Bundle()
            bundle.putString("registeType", "regisPhone")
            launchActivity(RegisterActivity::class.java, bundle)
            //带点渐变动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        //忘记密码
        fun toForgetPassword() {
            startActivity(Intent(this@LoginActivity, ForgetPasswordActivity::class.java))
            //带点渐变动画
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }

        fun login() {
            when {
                mViewModel.username.value.isEmpty() -> showMessage("请填写账号")
                mViewModel.password.get().isEmpty() -> showMessage("请填写密码")
//                else -> requestLoginRegisterViewModel.loginReq(
//                    mViewModel.username.value,
//                    mViewModel.password.get(),
//                    "1111"
//                )
            }
        }

    }


}
