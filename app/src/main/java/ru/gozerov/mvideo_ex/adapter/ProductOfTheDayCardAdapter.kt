package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.Product
import ru.gozerov.mvideo_ex.databinding.ItemProductOfTheDayBinding

class ProductOfTheDayCardAdapter: BaseRecyclerViewAdapter<Product>() {

    class ProductCardViewHolder(val binding: ItemProductOfTheDayBinding): BaseViewHolder<Product>(binding) {
        override fun bind(data: Product) {
            with(this.binding) {
                com.bumptech.glide.Glide.with(this.root)
                    .load(data.productImage)
                    .into(imgProductPlacement)

                txtNewProductPrice.text = data.newPrice
                txtOldProductPrice.text = data.oldPrice
                txtOldProductPrice.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                txtRating.text = data.rating.toString()
                txtReviews.text = root.context.getString(ru.gozerov.mvideo_ex.R.string.num_reviews, data.reviews)
                txtProductNaming.text = data.productName
            }
        }
    }

    override var data: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductOfTheDayBinding.inflate(inflater, parent, false)
        return ProductCardViewHolder(binding)
    }

}