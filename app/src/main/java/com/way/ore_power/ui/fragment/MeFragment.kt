package com.way.ore_power.ui.fragment

import android.os.Bundle
import com.way.ore_power.R
import com.way.ore_power.databinding.FragmentMeBinding
import com.way.ore_power.viewmodel.state.MeViewModel
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment

class MeFragment :  BaseFragment<MeViewModel, FragmentMeBinding>() {

    override fun layoutId() = R.layout.fragment_me
    override fun initView(savedInstanceState: Bundle?) {

    }


}
