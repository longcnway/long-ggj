package com.way.ore_power.viewmodel.request

import androidx.lifecycle.MutableLiveData
import com.way.ore_power.data.model.bean.HomeTitleItem
import me.hgj.jetpackmvvm.base.viewmodel.BaseViewModel

class RequestHomeViewModel: BaseViewModel(){

    var titleData: MutableLiveData<ArrayList<HomeTitleItem>> = MutableLiveData()

    fun getHomeTitleData() {
        val mList = ArrayList<HomeTitleItem>()
        mList.add(HomeTitleItem(0, "魔法星球1"))
        mList.add(HomeTitleItem(1, "魔法星球2"))
        titleData.postValue(mList)
    }


}
