package me.hgj.jetpackmvvm.base.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gyf.barlibrary.ImmersionBar
import me.hgj.jetpackmvvm.R
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel
import me.hgj.jetpackmvvm.ext.getVmClazz
import me.hgj.jetpackmvvm.network.manager.NetState
import me.hgj.jetpackmvvm.network.manager.NetworkStateManager
import me.hgj.jetpackmvvm.util.AppActivityManager

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/12
 * 描述　: ViewModelActivity基类，把ViewModel注入进来了
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity(){

    /**
     * 是否需要使用Databinding 供子类BaseVmDbActivity修改，用户请慎动
     */
    private var isUserDb = false

    lateinit var mViewModel: VM

    abstract fun layoutId(): Int

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun showLoading(message: String = "请求网络中...")

    abstract fun dismissLoading()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isUserDb) {
            setContentView(layoutId())
        } else {
            initDataBind()
        }
        init(savedInstanceState)
        AppActivityManager.getInstance().addActivity(this)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initIntent()
        initView(savedInstanceState)
        initBar()
        getData()
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
            onNetworkStateChanged(it)
        })
    }

    //获取intent值
    open fun initIntent() {

    }

    /**
     * 初始化状态栏
     */
    open fun initBar() {

    }

    /**
     * 初始数据
     */
    open fun getData() {

    }

    /**
     * 网络变化监听 子类重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     * 创建viewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 创建LiveData数据观察者
     */
    abstract fun createObserver()

    /**
     * 注册 UI 事件
     */
    private fun registerUiChange() {
        //显示弹窗
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading(
                if (it.isEmpty()) {
                    "请求网络中..."
                } else it
            )
        })
        //关闭弹窗
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
    }

    fun userDataBinding(isUserDb: Boolean) {
        this.isUserDb = isUserDb
    }

    /**
     * 供子类BaseVmDbActivity 初始化Databinding操作
     */
    open fun initDataBind() {}


    open fun launchActivity(cls: Class<*>, bundle: Bundle) {
        val intent = Intent(this, cls)
        intent.putExtra("bundle", bundle)
        startActivity(intent)
    }

    open fun launchActivity(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        ImmersionBar.with(this).destroy()
        AppActivityManager.getInstance().removeActivity(this)

    }

}