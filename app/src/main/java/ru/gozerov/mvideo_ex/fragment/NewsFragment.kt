package ru.gozerov.mvideo_ex.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ru.gozerov.core.*
import ru.gozerov.mvideo_ex.adapter.SimpleNewsAdapter
import ru.gozerov.mvideo_ex.databinding.FragmentNewsBinding
import ru.gozerov.mvideo_ex.viewmodel.NewsViewModel
import javax.inject.Inject

class NewsFragment: Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val simpleNewsAdapter = SimpleNewsAdapter()

    @Inject
    lateinit var factory: NewsViewModel.Factory

    private val viewModel: NewsViewModel by viewModels { factory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.data.collect { data ->
                binding.newsRecyclerView.setupAdapter(simpleNewsAdapter, data, LINEAR(VERTICAL))
            }
        }

    }

}