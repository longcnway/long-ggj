package com.way.ore_power.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.way.ore_power.R
import com.way.ore_power.data.model.bean.HomeTab1Item
import com.way.ore_power.databinding.FragmentHomeTab2Binding
import com.way.ore_power.ui.adapter.HomeTab1Adapter
import com.way.ore_power.viewmodel.state.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home_tab1.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment

class HomeTab2Fragment : BaseFragment<HomeViewModel, FragmentHomeTab2Binding>() {

    private var imgId = 0

    override fun layoutId() = R.layout.fragment_home_tab2

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
        val imgList = listOf(R.drawable.icon_00_shoucangjia,
            R.drawable.icon_01_muyangzuo, R.drawable.icon_02_jinniuzuo,
            R.drawable.icon_03_shuangzizuo, R.drawable.icon_04_juxiezuo,
            R.drawable.icon_05_shizizuo, R.drawable.icon_06_chunvzuo,
            R.drawable.icon_07_tianchengzuo, R.drawable.icon_08_tianhezuo,
            R.drawable.icon_09_sheshouzuo, R.drawable.icon_10_mojiezuo,
            R.drawable.icon_11_shuipingzuo, R.drawable.icon_12_shuangyuzuo)

        val itemList = ArrayList<HomeTab1Item>()

        imgId = imgList[(0..12).random()]
        for (id in 0..99){
            if (id !=0 && id % 9 == 0) {
                itemList.add(HomeTab1Item(id, imgId))
            } else {
                itemList.add(HomeTab1Item(id, imgList[(0..12).random()]))
            }
        }
        return itemList
    }

}

