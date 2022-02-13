package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.SaleCard
import ru.gozerov.mvideo_ex.databinding.ItemSaleCardBinding

class SaleCardAdapter: BaseRecyclerViewAdapter<SaleCard>() {

    class SaleCardViewHolder(val binding: ItemSaleCardBinding): BaseViewHolder<SaleCard>(binding) {
        override fun bind(data: SaleCard) {
            this.binding.root.tag = data
            Glide.with(this.binding.root)
                .load(data.backgroundImageUrl)
                .optionalFitCenter()
                .into(this.binding.imgSaleContent)
        }
    }

    override var data: List<SaleCard> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<SaleCard> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSaleCardBinding.inflate(inflater, parent, false)
        return SaleCardViewHolder(binding)
    }

}