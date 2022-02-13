package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.Product
import ru.gozerov.mvideo_ex.R
import ru.gozerov.mvideo_ex.databinding.ItemHitOfSaleBinding

class HitOfSaleCardAdapter: BaseRecyclerViewAdapter<Product>() {

    class HitOfSaleCardViewHolder(val binding: ItemHitOfSaleBinding): BaseViewHolder<Product>(binding) {
        override fun bind(data: Product) {
            with(this.binding) {
                Glide.with(this.root)
                    .load(data.productImage)
                    .into(imgProductPlacement)

                txtNewProductPrice.text = data.newPrice
                txtOldProductPrice.text = data.oldPrice
                txtOldProductPrice.paintFlags = android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
                txtRating.text = data.rating.toString()
                txtReviews.text = root.context.getString(R.string.num_reviews, data.reviews)
                txtProductNaming.text = data.productName
            }
        }
    }

    override var data: List<Product> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitOfSaleCardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHitOfSaleBinding.inflate(inflater, parent, false)
        return HitOfSaleCardViewHolder(binding)
    }

}