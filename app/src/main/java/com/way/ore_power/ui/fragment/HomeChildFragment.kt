package com.way.ore_power.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.way.ore_power.R
import com.way.ore_power.app.ext.init
import com.way.ore_power.app.ext.loadServiceInit
import com.way.ore_power.app.ext.showLoading
import com.way.ore_power.app.widght.recyclerview.DefineLoadMoreView
import com.way.ore_power.app.widght.recyclerview.SpaceItemDecoration
import com.way.ore_power.data.model.bean.HomeTitleItem
import com.way.ore_power.databinding.FragmentHomeChildBinding
import com.way.ore_power.ui.adapter.PointDetailAdapter
import com.way.ore_power.viewmodel.state.HomeViewModel
import com.kingja.loadsir.core.LoadService
import kotlinx.android.synthetic.main.include_recyclerview.*
import me.hgj.jetpackmvvm.demo.app.base.BaseFragment

class HomeChildFragment:
    BaseFragment<HomeViewModel, FragmentHomeChildBinding>(){

    //适配器
    private val pointDetailAdapter: PointDetailAdapter by lazy { PointDetailAdapter(arrayListOf()) }

    //界面状态管理者
    private lateinit var loadsir: LoadService<Any>

    //recyclerview的底部加载view 因为在首页要动态改变他的颜色，所以加了他这个字段
    private lateinit var footView: DefineLoadMoreView

    override fun layoutId()= R.layout.fragment_home_child

    override fun initView(savedInstanceState: Bundle?) {
        loadsir = loadServiceInit(swipeRefresh) {
            //点击重试时触发的操作
            loadsir.showLoading()
        }
        recyclerView.init(LinearLayoutManager(context), pointDetailAdapter).let {
            //因为首页要添加轮播图，所以我设置了firstNeedTop字段为false,即第一条数据不需要设置间距
            it.addItemDecoration(SpaceItemDecoration(0, ConvertUtils.dp2px(8f), false))
            if(it.headerCount==0){
                val displayMetrics = this@HomeChildFragment.resources.displayMetrics
                val widthSingle = displayMetrics.widthPixels
                val layoutParams = ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                )
                layoutParams.width = widthSingle

                val headview =
                    LayoutInflater.from(context).inflate(R.layout.include_home_child_head, null)
                headview.layoutParams=layoutParams
                recyclerView.addHeaderView(headview)
            }
        }
        var homeTitleItem = HomeTitleItem()
        homeTitleItem.name="126291211"
        var homeTitleItem2 = HomeTitleItem()
        homeTitleItem2.name="126291211"
        var list=ArrayList<HomeTitleItem>()
        list.add(homeTitleItem)
        list.add(homeTitleItem2)
        pointDetailAdapter.setList(list)

        //初始化 SwipeRefreshLayout
        swipeRefresh.init {
            //触发刷新监听时请求数据
        }
    }

    companion object {
        fun newInstance(name: String): HomeChildFragment {
            val args = Bundle()
//            args.putInt("cid", cid)
//            args.putBoolean("isNew", isNew)
            val fragment = HomeChildFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
