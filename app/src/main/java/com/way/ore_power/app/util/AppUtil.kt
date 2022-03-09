package com.way.ore_power.app.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.app.ActivityManager.RunningAppProcessInfo
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 *Created by wanWu.
 *Date:2021/5/19
 */
object AppUtil {
    private var mContext: Context? = null

    private fun AppUtil() {
        throw UnsupportedOperationException("you can't instantiate me...")
    }

    /**
     * 初始化工具类（需要在Application类注册初始化）
     *
     * @param context 上下文
     */
    fun init(context: Context) {
        mContext = context.applicationContext
        registerLiveEventBus()
    }

    private fun registerLiveEventBus() {
//        LiveEventBus.config()
//            .supportBroadcast(mContext)
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    fun getContext(): Context {
        if (mContext != null) {
            return mContext as Context
        }
        throw NullPointerException("you should init first")
    }

    /**
     * View获取Activity的工具
     *
     * @param view ui
     * @return Activity
     */
    @NonNull
    fun getActivity(view: View): Activity? {
        var context = view.context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        throw IllegalStateException("View $view is not attached to an Activity")
    }

    /**
     * 全局获取String的方法(获取xml资源里定义的String)
     *
     * @param id 资源Id
     * @return String
     */
    fun getString(@StringRes id: Int): String? {
        return mContext!!.resources.getString(id)
    }

    /**
     * The `fragment` is added to the container ui with id `frameId`. The operation is
     * performed by the `fragmentManager`.
     */
    fun addFragmentToActivity(
        @NonNull fragmentManager: FragmentManager,
        @NonNull fragment: Fragment, frameId: Int
    ) {
        checkNotNull<Any>(fragmentManager)
        checkNotNull<Any>(fragment)
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.add(frameId, fragment)
        transaction.commit()
    }


    fun <T> checkNotNull(obj: T?): T {
        if (obj == null) {
            throw NullPointerException()
        }
        return obj
    }



    /**
     * 获取App包名
     *
     * @param context 上下文
     * @return App包名
     */
    fun getAppPackageName(context: Context): String? {
        return context.packageName
    }


    /**
     * 判断App是否处于前台
     *
     * @param context 上下文
     * @return `true`: 是<br></br>`false`: 否
     */
    fun isAppForeground(context: Context): Boolean {
        val manager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val infos =
            manager.runningAppProcesses
        if (infos == null || infos.size == 0) {
            return false
        }
        for (info in infos) {
            if (info.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return info.processName == context.packageName
            }
        }
        return false
    }


    /**
     * 安装APK
     *
     * @param context
     * @param APK_PATH
     */
    fun InstallAPK(
        context: Context,
        APK_PATH: String
    ) { //提示安装APK
        val i = Intent(Intent.ACTION_VIEW)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        i.setDataAndType(
            Uri.parse("file://$APK_PATH"),
            "application/vnd.android.package-archive"
        )
        context.startActivity(i)
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }
        }
        return null
    }


    /**
     * 判断当前App处于前台还是后台
     *
     * 需添加权限 `<uses-permission android:name="android.permission.GET_TASKS"/>`
     *
     * 并且必须是系统应用该方法才有效
     *
     * @param context 上下文
     * @return `true`: 后台<br></br>`false`: 前台
     */
    @SuppressLint("NewApi")
    fun isAppBackground(context: Context): Boolean {
        val am =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val tasks = am.getRunningTasks(1)
        if (!tasks.isEmpty()) {
            val topActivity = tasks[0].topActivity
            return topActivity!!.packageName != context.packageName
        }
        return false
    }

    /**
     * 关闭系统软键盘
     *
     * @param activity
     * @param view
     */
    fun cancelSystemKeyBoard(activity: Activity, view: View) {
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0) //强制隐藏键盘
    }

    /**
     * 开启系统软键盘
     *
     * @param activity
     * @param view
     */
    fun openSystemKeyBoard(activity: Activity, view: View?) {
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    /**
     * 系统软键盘是否打开
     *
     * @param activity
     */
    fun isOpenSystemKeyBoard(activity: Activity): Boolean? {
        val imm =
            activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isActive //isOpen若返回true，则表示输入法打开
    }

    private var mExitTime: Long = 0

    /**
     * 获取App版本码
     *
     * @param context 上下文
     * @return App版本码
     */
    fun getAppVersionCode(context: Context): Int {
        return getAppVersionCode(context, context.packageName)
    }

    /**
     * 获取App版本码
     *
     * @param context     上下文
     * @param packageName 包名
     * @return App版本码
     */
    fun getAppVersionCode(context: Context, packageName: String?): Int {
        return if (TextUtils.isEmpty(packageName)) {
            -1
        } else try {
            val pm = context.packageManager
            val pi = packageName?.let { pm.getPackageInfo(it, 0) }
            pi?.versionCode ?: -1
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            -1
        }
    }

}