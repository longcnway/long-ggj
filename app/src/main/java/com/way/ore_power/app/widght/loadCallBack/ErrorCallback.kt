package com.way.ore_power.app.widght.loadCallBack

import com.way.ore_power.R
import com.kingja.loadsir.callback.Callback


class ErrorCallback : Callback() {

    override fun onCreateView(): Int {
        return R.layout.layout_error
    }

}