package ru.gozerov.mvideo_ex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.google.android.material.navigation.NavigationBarView
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.mvideo_ex.R
import ru.gozerov.mvideo_ex.databinding.FragmentTabsBinding
import ru.gozerov.mvideo_ex.viewmodel.TabsViewModel

class TabsFragment: Fragment(R.layout.fragment_tabs) {

    private lateinit var binding: FragmentTabsBinding

    private val viewModel: TabsViewModel by viewModels {
        TabsViewModel.Factory(router, this, bundleOf("isInitialized" to false))
    }

    private val navigator: AppNavigator by lazy { AppNavigator(requireActivity(), R.id.fragmentContainer) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTabsBinding.inflate(layoutInflater, container, false)
        setupBottomNavigation()
        setupNavigation()
        return binding.root
    }

    override fun onResume() {
        navigatorHolder.setNavigator(navigator)
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    private fun setupNavigation() {
        val data = arguments?.getSerializable("EXTRA_ADAPTERS_DATA") as AdaptersData
        viewModel.setupNavigation(data)

        parentFragmentManager.setFragmentResultListener("RESULT_KEY", viewLifecycleOwner) { _, result ->
            if (result.getBoolean("tabsIsVisible"))
                binding.bottomNavigationView.visibility = View.VISIBLE
            else
                binding.bottomNavigationView.visibility = View.GONE
        }
    }

    private fun setupBottomNavigation() {
        val bottomNavigation = binding.bottomNavigationView
        (bottomNavigation as NavigationBarView).setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.firstMenu -> {
                    if (parentFragmentManager.fragments.contains(MainFragment())) {
                        //val data = arguments?.getSerializable("EXTRA_ADAPTERS_DATA") as AdaptersData
                        //router.navigateTo(MainActivity.Screens.mainFragment(data))
                    }
                    return@setOnItemSelectedListener true
                }
                R.id.secondMenu -> return@setOnItemSelectedListener true
                R.id.thirdMenu -> return@setOnItemSelectedListener true
                else -> throw Exception()
            }
        }
    }

    companion object {

        private const val EXTRA_ADAPTERS_DATA = "EXTRA_ADAPTERS_DATA"

        fun getNewInstance(adaptersData: AdaptersData): TabsFragment {
            return TabsFragment().apply {
                arguments = bundleOf(EXTRA_ADAPTERS_DATA to adaptersData)
            }
        }

        private val cicerone = Cicerone.create()
        val router get() = cicerone.router
        private val navigatorHolder get() = cicerone.getNavigatorHolder()
    }

}