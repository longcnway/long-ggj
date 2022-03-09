package com.way.ore_power.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


/**
 * 标题分类
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class HomeTab1Item(var id:Int = 0, var img:Int = 0):Parcelable
