package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.data.remote.news.models.DataSimpleNews
import ru.gozerov.domain.entity.news.SimpleNews
import ru.gozerov.mvideo_ex.databinding.ItemSimpleNewsBinding

class SimpleNewsAdapter: BaseRecyclerViewAdapter<SimpleNews>() {

    class SimpleNewsViewHolder(private val binding: ItemSimpleNewsBinding): BaseViewHolder<SimpleNews>(binding) {

        override fun bind(data: SimpleNews) {
            binding.txtTitle.text = data.title
            binding.txtContent.text = data.content
        }

    }

    override var data: List<SimpleNews> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleNewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSimpleNewsBinding.inflate(inflater, parent, false)
        return SimpleNewsViewHolder(binding)
    }
}