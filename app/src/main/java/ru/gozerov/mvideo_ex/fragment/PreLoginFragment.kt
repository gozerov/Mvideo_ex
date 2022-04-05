package ru.gozerov.mvideo_ex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import ru.gozerov.core.*
import ru.gozerov.mvideo_ex.R
import ru.gozerov.mvideo_ex.adapter.BonusProgramCardAdapter
import ru.gozerov.mvideo_ex.databinding.FragmentPreLoginBinding
import ru.gozerov.mvideo_ex.navigation.Screens
import ru.gozerov.mvideo_ex.viewmodel.PreLoginViewModel

class PreLoginFragment: Fragment() {

    private lateinit var binding: FragmentPreLoginBinding

    private val viewModel: PreLoginViewModel by viewModels {
        PreLoginViewModel.Factory(
            R.drawable.ic_baseline_arrow_circle_up_24
        )
    }

    private val mAdapter = BonusProgramCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        sendResult("tabsIsVisible", false)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPreLoginBinding.inflate(inflater, container, false)
        binding.loginButton.setOnClickListener {
            TabsFragment.router.navigateTo(Screens.loginByPhoneFragment())
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.adapterDataFlow.collect { data ->
                binding.bonusProgramInfoRecyclerView.setupAdapter(mAdapter, data, LINEAR(VERTICAL))
            }
        }
    }

}