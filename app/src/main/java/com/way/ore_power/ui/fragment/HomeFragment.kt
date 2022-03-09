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

class HomeFragment : BaseFragment<HomeViewModel,FragmentViewpagerBinding>() {

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>
    //fragment集合
    var fragments: ArrayList<Fragment> = arrayListOf()
    //标题集合
    var mDataList: ArrayList<HomeTitleItem> = arrayListOf()

    private val requestHomeViewModel: RequestHomeViewModel by viewModels()


    override fun layoutId() = R.layout.fragment_viewpager


    override fun initView(savedInstanceState: Bundle?) {

        loadsir = loadServiceInit(view_pager) {
            //点击重试时触发的操作
            loadsir.showLoading()
        }
        //初始化viewpager2
        view_pager.init(this, fragments)
        //初始化 magic_indicator
        magic_indicator.bindViewPager2(view_pager, mDataList)
    }

    /**
     * 懒加载
     */
    override fun lazyLoadData() {
        //设置界面 加载中
        loadsir.showLoading()
        //请求标题数据
        requestHomeViewModel.getHomeTitleData()
    }

    override fun createObserver() {
        requestHomeViewModel.titleData.observe(viewLifecycleOwner, Observer { it ->
            mDataList.clear()
            fragments.clear()

            mDataList.addAll(it)
            fragments.add(HomeTab1Fragment())
            fragments.add(HomeTab2Fragment())
            fragments.add(HomeTab3Fragment())
            magic_indicator.navigator.notifyDataSetChanged()
            view_pager.adapter?.notifyDataSetChanged()
            view_pager.offscreenPageLimit = fragments.size
            loadsir.showSuccess()
        })
    }

}
