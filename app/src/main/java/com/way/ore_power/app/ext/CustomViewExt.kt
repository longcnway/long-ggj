package com.way.ore_power.app.ext

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.yanzhenjie.recyclerview.SwipeRecyclerView
import com.way.ore_power.R
import com.way.ore_power.app.App
import com.way.ore_power.app.network.stateCallback.ListDataUiState
import com.way.ore_power.app.util.SettingUtil
import com.way.ore_power.app.widght.loadCallBack.EmptyCallback
import com.way.ore_power.app.widght.loadCallBack.ErrorCallback
import com.way.ore_power.app.widght.loadCallBack.LoadingCallback
import com.way.ore_power.app.widght.recyclerview.DefineLoadMoreView
import com.way.ore_power.app.widght.viewpager.ScaleTransitionPagerTitleView
import com.way.ore_power.data.model.bean.HomeTitleItem
import com.way.ore_power.ui.fragment.HomeFragment
import com.way.ore_power.ui.fragment.Menu5Fragment

import me.hgj.jetpackmvvm.ext.util.toHtml
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator


/**
 * ?????????: hegaojian
 * ?????????: 2020/2/20
 * ?????????:????????????????????????????????????
 */

fun LoadService<*>.setErrorText(message: String) {
    if (message.isNotEmpty()) {
        this.setCallBack(ErrorCallback::class.java) { _, view ->
            view.findViewById<TextView>(R.id.error_text).text = message
        }
    }
}

/**
 * ??????????????????
 * @param message ?????????????????????????????????
 */
fun LoadService<*>.showError(message: String = "") {
    this.setErrorText(message)
    this.showCallback(ErrorCallback::class.java)
}

/**
 * ???????????????
 */
fun LoadService<*>.showEmpty() {
    this.showCallback(EmptyCallback::class.java)
}

/**
 * ???????????????
 */
fun LoadService<*>.showLoading() {
    this.showCallback(LoadingCallback::class.java)
}

fun loadServiceInit(view: View, callback: () -> Unit): LoadService<Any> {
    val loadsir = LoadSir.getDefault().register(view) {
        //??????????????????????????????
        callback.invoke()
    }
    loadsir.showSuccess()
    SettingUtil.setLoadingColor(SettingUtil.getColor(App.instance), loadsir)
    return loadsir
}

//???????????????Recyclerview
fun RecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): RecyclerView {
    layoutManager = layoutManger
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

//??????SwipeRecyclerView
fun SwipeRecyclerView.init(
    layoutManger: RecyclerView.LayoutManager,
    bindAdapter: RecyclerView.Adapter<*>,
    isScroll: Boolean = true
): SwipeRecyclerView {
    layoutManager = layoutManger
    setHasFixedSize(true)
    adapter = bindAdapter
    isNestedScrollingEnabled = isScroll
    return this
}

fun SwipeRecyclerView.initFooter(loadmoreListener: SwipeRecyclerView.LoadMoreListener): DefineLoadMoreView {
    val footerView = DefineLoadMoreView(App.instance)
    //?????????????????????
    footerView.setLoadViewColor(SettingUtil.getOneColorStateList(App.instance))
    //????????????????????????
    footerView.setmLoadMoreListener(SwipeRecyclerView.LoadMoreListener {
        footerView.onLoading()
        loadmoreListener.onLoadMore()
    })
    this.run {
        //????????????????????????
        addFooterView(footerView)
        setLoadMoreView(footerView)
        //????????????????????????
        setLoadMoreListener(loadmoreListener)
    }
    return footerView
}

fun RecyclerView.initFloatBtn(floatbtn: FloatingActionButton) {
    //??????recyclerview?????????????????????????????????????????????????????????????????????
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        @SuppressLint("RestrictedApi")
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!canScrollVertically(-1)) {
                floatbtn.visibility = View.INVISIBLE
            }
        }
    })
    floatbtn.backgroundTintList = SettingUtil.getOneColorStateList(App.instance)
    floatbtn.setOnClickListener {
        val layoutManager = layoutManager as LinearLayoutManager
        //????????????recyclerview ?????????????????????????????????????????????40????????????????????????????????????????????????????????????????????????
        if (layoutManager.findLastVisibleItemPosition() >= 40) {
            scrollToPosition(0)//?????????????????????????????????(??????)
        } else {
            smoothScrollToPosition(0)//??????????????????????????????(?????????)
        }
    }
}

//????????? SwipeRefreshLayout
fun SwipeRefreshLayout.init(onRefreshListener: () -> Unit) {
    this.run {
        setOnRefreshListener {
            onRefreshListener.invoke()
        }
        //??????????????????
        setColorSchemeColors(SettingUtil.getColor(App.instance))
    }
}

/**
 * ??????????????????toolbar ???????????????
 */
fun Toolbar.init(titleStr: String = ""): Toolbar {
    setBackgroundColor(SettingUtil.getColor(App.instance))
    title = titleStr
    return this
}

/**
 * ????????????????????????toolbar
 */
fun Toolbar.initClose(
    titleStr: String = "",
    backImg: Int = R.drawable.icon_go_back,
    onBack: (toolbar: Toolbar) -> Unit
): Toolbar {
    title = titleStr.toHtml()
    setNavigationIcon(backImg)
    setNavigationOnClickListener { onBack.invoke(this) }
    return this
}


//??????????????????????????????
fun BaseQuickAdapter<*, *>.setAdapterAnimation(mode: Int) {
    //??????0????????????????????? ????????????
    if (mode == 0) {
        this.animationEnable = false
    } else {
        this.animationEnable = true
        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[mode - 1])
    }
}

fun MagicIndicator.bindViewPager2(
    viewPager: ViewPager2,
    mDataList: ArrayList<HomeTitleItem> = arrayListOf(),
    mStringList: ArrayList<String> = arrayListOf(),
    action: (index: Int) -> Unit = {}
) {
    val commonNavigator = CommonNavigator(App.instance)
    commonNavigator.adapter = object : CommonNavigatorAdapter() {
        override fun getCount(): Int {
            return if (mDataList.size != 0) {
                mDataList.size
            } else {
                mStringList.size
            }
        }

        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
            return ScaleTransitionPagerTitleView(App.instance).apply {
                text = if (mDataList.size != 0) {
                    mDataList[index].name.toHtml()
                } else {
                    mStringList[index].toHtml()
                }
                textSize = 21f
                normalColor = ContextCompat.getColor(getContext(), R.color.color_99ffffff)
                selectedColor = ContextCompat.getColor(getContext(), R.color.color_white)
                setOnClickListener {
                    viewPager.currentItem = index
                    action.invoke(index)
                }
            }
        }

        override fun getIndicator(context: Context): IPagerIndicator {
            return LinePagerIndicator(context).apply {
                mode = LinePagerIndicator.MODE_EXACTLY
                //??????????????????
                lineHeight = UIUtil.dip2px(App.instance, 3.0).toFloat()
                lineWidth = UIUtil.dip2px(App.instance, 30.0).toFloat()
                //???????????????
                roundRadius = UIUtil.dip2px(App.instance, 6.0).toFloat()
                startInterpolator = AccelerateInterpolator()
                endInterpolator = DecelerateInterpolator(2.0f)
                //???????????????
                setColors(Color.WHITE)
            }
        }
    }
    this.navigator = commonNavigator

    viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            this@bindViewPager2.onPageSelected(position)
            action.invoke(position)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            this@bindViewPager2.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)
            this@bindViewPager2.onPageScrollStateChanged(state)
        }
    })
}

fun ViewPager2.init(
    fragment: Fragment,
    fragments: ArrayList<Fragment>,
    isUserInputEnabled: Boolean = true
): ViewPager2 {
    //???????????????
    this.isUserInputEnabled = isUserInputEnabled
    //???????????????
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int) = fragments[position]
        override fun getItemCount() = fragments.size
    }
    return this
}

fun ViewPager2.initMain(fragment: Fragment): ViewPager2 {
    //???????????????
    this.isUserInputEnabled = false
    this.offscreenPageLimit = 5
    //???????????????
    adapter = object : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> {
                    HomeFragment()
                }
                1 -> {
                    Menu5Fragment()
                }
                else -> {
                    HomeFragment()
                }
            }
        }

        override fun getItemCount() = 5
    }
    return this
}


/**
 * ???????????????
 */
fun hideSoftKeyboard(activity: Activity?) {
    activity?.let { act ->
        val view = act.currentFocus
        view?.let {
            val inputMethodManager =
                act.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(
                view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}

/**
 * ??????????????????
 */
fun <T> loadListData(
    data: ListDataUiState<T>,
    baseQuickAdapter: BaseQuickAdapter<T, *>,
    loadService: LoadService<*>,
    recyclerView: SwipeRecyclerView,
    swipeRefreshLayout: SwipeRefreshLayout
) {
    swipeRefreshLayout.isRefreshing = false
    recyclerView.loadMoreFinish(data.isEmpty, data.hasMore)
    if (data.isSuccess) {
        //??????
        when {
            //???????????????????????? ?????????????????????
            data.isFirstEmpty -> {
                loadService.showEmpty()
            }
            //????????????
            data.isRefresh -> {
                baseQuickAdapter.setList(data.listData)
                loadService.showSuccess()
            }
            //???????????????
            else -> {
                baseQuickAdapter.addData(data.listData)
                loadService.showSuccess()
            }
        }
    } else {
        //??????
        if (data.isRefresh) {
            //??????????????????????????????????????????????????????????????????
            loadService.showError(data.errMessage)
        } else {
            recyclerView.loadMoreError(0, data.errMessage)
        }
    }
}