package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.BonusProgram
import ru.gozerov.mvideo_ex.databinding.ItemBonusProgramBinding

class BonusProgramCardAdapter: BaseRecyclerViewAdapter<BonusProgram>() {

    class BonusProgramViewHolder(private val binding: ItemBonusProgramBinding): BaseViewHolder<BonusProgram>(binding) {

        override fun bind(data: BonusProgram) {
            with(this.binding) {
                txtTitle.text = data.title
                txtDescription.text = data.description
                imgIcon.setImageResource(data.icon)
            }
        }

    }

    override var data: List<BonusProgram> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BonusProgram> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBonusProgramBinding.inflate(inflater, parent, false)
        return BonusProgramViewHolder(binding)
    }
}
