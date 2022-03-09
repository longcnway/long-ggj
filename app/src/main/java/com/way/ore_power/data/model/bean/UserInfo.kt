package com.way.ore_power.data.model.bean

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
class UserInfo(
    var userId: String = "",
    var nickname: String = ""
):Parcelable
