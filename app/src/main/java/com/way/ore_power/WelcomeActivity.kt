package com.way.ore_power

import android.content.Intent
import android.os.Bundle
import com.way.ore_power.app.base.BaseActivity
import com.way.ore_power.databinding.ActivityWelcomeBinding
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class WelcomeActivity : BaseActivity<BaseViewModel, ActivityWelcomeBinding>(){
    override fun layoutId(): Int {
        return R.layout.activity_welcome
    }

    override fun initView(savedInstanceState: Bundle?) {
        //防止出现按Home键回到桌面时，再次点击重新进入该界面bug
        if (intent.flags and Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT !== 0) {
            finish()
            return
        }
//        if (!CacheUtil.isLogin()) {
//            //不是第一次打开App 0.3秒后自动跳转到主页
//                startActivity(Intent(this, LoginActivity::class.java))
//                finish()
//                //带点渐变动画
//                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//        } else {
            //不是第一次打开App 0.3秒后自动跳转到主页
                startActivity(Intent(this, MainActivity::class.java))
                finish()
                //带点渐变动画
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
//        }
    }

}