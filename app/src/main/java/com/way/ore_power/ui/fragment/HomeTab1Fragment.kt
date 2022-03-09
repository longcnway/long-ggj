package com.way.ore_power.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.way.ore_power.R
import com.way.ore_power.data.model.bean.HomeTab1Item
import com.way.ore_power.databinding.FragmentHomeTab1Binding
import com.way.ore_power.ui.adapter.HomeTab1Adapter
import com.way.ore_power.viewmodel.state.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home_tab1.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment

class HomeTab1Fragment : BaseFragment<HomeViewModel, FragmentHomeTab1Binding>() {

    private var imgId = 0

    override fun layoutId() = R.layout.fragment_home_tab1

    override fun initView(savedInstanceState: Bundle?) {
        mDatabind.click = ProxyClick()
        rvList.layoutManager = GridLayoutManager(activity, 8)
        rvList!!.isNestedScrollingEnabled = true
        rvList!!.setHasFixedSize(true)
        rvList.adapter = HomeTab1Adapter(getHomeTab1Data())
    }

    inner class ProxyClick {

        //魔法球
        fun powerBall(){
            ivPowerSign.visibility = View.VISIBLE
            ivPowerSign.setImageResource(imgId)
        }

        //再玩一次
        fun againPlay() {
            ivPowerSign.visibility = View.GONE
            rvList.adapter = HomeTab1Adapter(getHomeTab1Data())
        }
    }

    /**
     * 初始化数据
     */
    private fun getHomeTab1Data(): MutableList<HomeTab1Item> {
        val imgList = listOf(R.drawable.power_anquanyinsi,
            R.drawable.power_banquan, R.drawable.power_cangchucangku,
            R.drawable.power_cunchu, R.drawable.power_erweima,
            R.drawable.power_fenbushi, R.drawable.power_gongyi,
            R.drawable.power_haiguangangkou, R.drawable.power_kaifang,
            R.drawable.power_kuaisugaoxiao, R.drawable.power_maijiagouwuche,
            R.drawable.power_quanqiukuajing, R.drawable.power_qukuai,
            R.drawable.power_renminbi, R.drawable.power_shangyehuaquanqiu,
            R.drawable.power_user, R.drawable.power_wangluo)

        val itemList = ArrayList<HomeTab1Item>()

        imgId = imgList[(0..16).random()]
        for (id in 0..99){
            if (id !=0 && id % 9 == 0) {
                itemList.add(HomeTab1Item(id, imgId))
            } else {
                itemList.add(HomeTab1Item(id, imgList[(0..16).random()]))
            }
        }
        return itemList
    }

}
