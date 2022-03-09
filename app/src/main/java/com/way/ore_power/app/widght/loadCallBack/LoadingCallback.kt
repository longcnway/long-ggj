package com.way.ore_power.app.widght.loadCallBack

import android.content.Context
import android.view.View
import com.way.ore_power.R
import com.kingja.loadsir.callback.Callback


class LoadingCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_loading
    }

    override fun onReloadEvent(context: Context?, view: View?): Boolean {
        return true
    }
}
