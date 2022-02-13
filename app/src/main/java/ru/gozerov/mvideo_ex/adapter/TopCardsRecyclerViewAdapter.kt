package ru.gozerov.mvideo_ex.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.shareIn
import ru.gozerov.core.BaseRecyclerViewAdapter
import ru.gozerov.core.BaseViewHolder
import ru.gozerov.domain.entity.TopCard
import ru.gozerov.mvideo_ex.databinding.ItemTopCardBinding
import ru.gozerov.mvideo_ex.databinding.ItemTopLoginBinding
import kotlin.coroutines.coroutineContext

class TopCardsRecyclerViewAdapter: BaseRecyclerViewAdapter<TopCard>(), View.OnClickListener {

    private val _clickEvent = MutableSharedFlow<TopCard>(0, 1, BufferOverflow.DROP_OLDEST)
    val clickEvent: SharedFlow<TopCard> = _clickEvent.asSharedFlow()

    class TopCardsViewHolder(private val binding: ItemTopCardBinding): BaseViewHolder<TopCard>(binding) {

        override fun bind(data: TopCard) {
            binding.title.text = data.title
            binding.imgIcon.setImageResource(data.icon)
        }

    }

    class LoginTopCardViewHolder(private val binding: ItemTopLoginBinding): BaseViewHolder<TopCard>(binding) {

        override fun bind(data: TopCard) {
            binding.title.text = data.title
            binding.imgIcon.setImageResource(data.icon)
        }

    }

    override var data: List<TopCard> = emptyList()
        set(value) {
            val diffUtilCallback = TopCardDiffCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffUtilCallback)
            field = value
            diffResult.dispatchUpdatesTo(this)
        }

    override fun getItemViewType(position: Int): Int {
        return when(data[position].title) {
            "Войти" -> 1
            else -> 0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TopCard> {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            1 ->  {
                val binding = ItemTopLoginBinding.inflate(inflater, parent, false)
                binding.root.setOnClickListener(this)
                LoginTopCardViewHolder(binding)
            }
            else -> {
                val binding = ItemTopCardBinding.inflate(inflater, parent, false)
                binding.root.setOnClickListener(this)
                TopCardsViewHolder(binding)
            }
        }
    }

    override fun onClick(view: View?) {
        val topCard = view?.tag!! as TopCard
        _clickEvent.tryEmit(topCard)
    }

}