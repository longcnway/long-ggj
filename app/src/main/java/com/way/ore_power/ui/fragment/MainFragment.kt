package com.way.ore_power.ui.fragment

import android.os.Bundle
import com.way.ore_power.R
import com.way.ore_power.app.ext.initMain
import com.way.ore_power.databinding.FragmentMainBinding
import com.way.ore_power.viewmodel.state.MainViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment


class MainFragment : BaseFragment<MainViewModel,FragmentMainBinding>(){
    override fun layoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(savedInstanceState: Bundle?) {
        //初始化viewpager2
        mainViewpager.initMain(this)
        rdgHome.setOnCheckedChangeListener { radioGroup, checkedId ->
            when (checkedId) {
                R.id.rbt_user_home_bottom1 -> mainViewpager.currentItem = 0
                R.id.rbt_user_home_bottom2 -> mainViewpager.currentItem = 1
                R.id.rbt_user_home_bottom3 -> mainViewpager.currentItem = 2
                R.id.rbt_user_home_bottom4 -> mainViewpager.currentItem = 3
                R.id.rbt_user_home_bottom5 -> mainViewpager.currentItem = 4
            }
        }
    }

}