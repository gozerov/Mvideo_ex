package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.MagContent
import ru.gozerov.mvideo_ex.databinding.ItemMagCardBinding

class MagCardAdapter: BaseRecyclerViewAdapter<MagContent>() {

    class MagCardViewHolder(val binding: ItemMagCardBinding): BaseViewHolder<MagContent>(binding) {
        override fun bind(data: MagContent) {
            with(this.binding) {
                txtMagType.text = data.magType
                txtMagContentDescription.text = data.shortDescription

                val requestOptions = RequestOptions().transform(CenterCrop(), RoundedCorners(8))
                Glide.with(root)
                    .load(data.magBackground)
                    .apply(requestOptions)
                    .into(imgBackground)
            }
        }
    }

    override var data: List<MagContent> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<MagContent> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMagCardBinding.inflate(inflater, parent, false)
        return MagCardViewHolder(binding)
    }

}