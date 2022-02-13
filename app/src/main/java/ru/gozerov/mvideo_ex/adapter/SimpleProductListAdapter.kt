package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.ProductSimple
import ru.gozerov.mvideo_ex.databinding.ItemProductSimpleBinding

class SimpleProductListAdapter: BaseRecyclerViewAdapter<ProductSimple>() {

    class SimpleProductViewHolder(val binding: ItemProductSimpleBinding): BaseViewHolder<ProductSimple>(binding) {
        override fun bind(data: ProductSimple) {
            with(this.binding) {
                txtProductPrice.text = data.price
                txtProductSimpleName.text = data.productName
                com.bumptech.glide.Glide.with(root)
                    .load(data.image)
                    .into(imgProduct)
            }
        }
    }

    override var data: List<ProductSimple> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ProductSimple> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductSimpleBinding.inflate(inflater, parent, false)
        return SimpleProductViewHolder(binding)
    }

}