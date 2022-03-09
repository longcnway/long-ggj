package com.way.ore_power.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.way.ore_power.R
import com.way.ore_power.data.model.bean.HomeTitleItem

class PointDetailAdapter(data: MutableList<HomeTitleItem>?):
    BaseQuickAdapter<HomeTitleItem, BaseViewHolder>(R.layout.layout_point_detail_item,data){

    override fun convert(holder: BaseViewHolder, item: HomeTitleItem) {
        holder.setText(R.id.tvPointId,item.name)
        holder.setText(R.id.tvWorkStatus,"正常")
        holder.setText(R.id.tvAllTicketsOut,item.name)


    }


}
