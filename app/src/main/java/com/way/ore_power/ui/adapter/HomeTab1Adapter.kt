package com.way.ore_power.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.way.ore_power.R
import com.way.ore_power.data.model.bean.HomeTab1Item

class HomeTab1Adapter(data: MutableList<HomeTab1Item>?):
    BaseQuickAdapter<HomeTab1Item, BaseViewHolder>(R.layout.layout_home_tab1_item, data){

    override fun convert(holder: BaseViewHolder, item: HomeTab1Item) {

        holder.setText(R.id.tvNumber, item.id.toString())
        holder.setImageResource(R.id.ivSign, item.img)

    }

}
