package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.gozerov.core.*
import ru.gozerov.mvideo_ex.databinding.ItemViewPagerBinding

class TrendsAdapter: BaseRecyclerViewAdapter<SimpleProductListAdapter>() {

    class TrendsViewHolder(val binding: ItemViewPagerBinding): BaseViewHolder<SimpleProductListAdapter>(binding) {
        override fun bind(data: SimpleProductListAdapter) {
            this.binding.productsVerticalRecyclerView.setupAdapter(data, data.data, LINEAR(VERTICAL))
        }
    }

    override var data: List<SimpleProductListAdapter> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewPagerBinding.inflate(inflater, parent, false)
        return TrendsViewHolder(binding)
    }

}