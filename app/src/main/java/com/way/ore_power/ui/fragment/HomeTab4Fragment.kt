package com.way.ore_power.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.way.ore_power.R
import com.way.ore_power.app.ext.bindViewPager2
import com.way.ore_power.app.ext.init
import com.way.ore_power.app.ext.loadServiceInit
import com.way.ore_power.app.ext.showLoading
import com.way.ore_power.data.model.bean.HomeTitleItem
import com.way.ore_power.databinding.FragmentViewpagerBinding
import com.way.ore_power.viewmodel.request.RequestHomeViewModel
import com.way.ore_power.viewmodel.state.HomeViewModel
import com.kingja.loadsir.core.LoadService
import kotlinx.android.synthetic.main.include_viewpager.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment


class HomeTab4Fragment : BaseFragment<HomeViewModel,FragmentViewpagerBinding>() {


    override fun layoutId() = R.layout.fragment_home_tab4


    override fun initView(savedInstanceState: Bundle?) {

    }

}
