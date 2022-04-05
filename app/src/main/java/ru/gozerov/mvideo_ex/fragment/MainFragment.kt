package ru.gozerov.mvideo_ex.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import ru.gozerov.core.*
import ru.gozerov.data.repository.cards.Adapters.HIT_OF_SALE_CARD_ADAPTER
import ru.gozerov.data.repository.cards.Adapters.MAG_CARDS_ADAPTER
import ru.gozerov.data.repository.cards.Adapters.MAIN_VIEW_PAGER_ADAPTER
import ru.gozerov.data.repository.cards.Adapters.PRODUCT_OF_THE_DAY_CARD_ADAPTER
import ru.gozerov.data.repository.cards.Adapters.SALE_CARD_ADAPTER
import ru.gozerov.data.repository.cards.Adapters.SALE_CARD_ADAPTER_BOTTOM
import ru.gozerov.data.repository.cards.Adapters.TOP_CARD_ADAPTER
import ru.gozerov.data.repository.cards.Adapters.WATCHED_PRODUCTS_CARD_ADAPTER
import ru.gozerov.domain.entity.*
import ru.gozerov.mvideo_ex.adapter.*
import ru.gozerov.mvideo_ex.databinding.FragmentMainBinding
import ru.gozerov.mvideo_ex.navigation.Screens
import ru.gozerov.mvideo_ex.viewmodel.MainFragmentViewModel

class MainFragment: Fragment() {

    private val topCardsAdapter = TopCardsRecyclerViewAdapter()
    private val saleCardsAdapter = SaleCardAdapter()
    private val productOfTheDayCardAdapter = ProductOfTheDayCardAdapter()
    private val hitOfSaleCardAdapter = HitOfSaleCardAdapter()
    private val mainViewPagerAdapter = TrendsAdapter()
    private val magCardsAdapter = MagCardAdapter()
    private val watchedProductsCardAdapter = WatchedProductsCardAdapter()

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        sendResult("tabsIsVisible", true)
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            topCardsAdapter.clickEvent.collect {
                TabsFragment.router.navigateTo(Screens.preLoginFragment())
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAdapters()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        if (viewModel.position!=0)
            binding.mainScrollView.verticalScrollbarPosition = viewModel.position
        super.onResume()
    }

    override fun onPause() {
        viewModel.position = binding.mainScrollView.verticalScrollbarPosition
        super.onPause()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupAdapters() {
        val adaptersData = arguments?.get(EXTRA_ADAPTERS_DATA)!! as AdaptersData
        with(binding) {
            topCardsRecyclerView.setupAdapter(topCardsAdapter, adaptersData.data.getValue(TOP_CARD_ADAPTER) as List<TopCard>, LINEAR(HORIZONTAL))
            saleCardsRecyclerView.setupAdapter(saleCardsAdapter, adaptersData.data.getValue(SALE_CARD_ADAPTER) as List<SaleCard>, LINEAR(HORIZONTAL))
            productsOfTheDayRecyclerView.setupAdapter(productOfTheDayCardAdapter, adaptersData.data.getValue(PRODUCT_OF_THE_DAY_CARD_ADAPTER) as List<Product>, LINEAR(HORIZONTAL))
            hitsOfSalesRecyclerView.setupAdapter(hitOfSaleCardAdapter, adaptersData.data.getValue(HIT_OF_SALE_CARD_ADAPTER) as List<Product>, LINEAR(HORIZONTAL))
            saleCardsRecyclerViewBottom.setupAdapter(saleCardsAdapter, adaptersData.data.getValue(SALE_CARD_ADAPTER_BOTTOM) as List<SaleCard>, LINEAR(HORIZONTAL))
            magRecyclerView.setupAdapter(magCardsAdapter, adaptersData.data.getValue(MAG_CARDS_ADAPTER) as List<MagContent>, LINEAR(HORIZONTAL))
            setupMainViewPager(adaptersData.data.getValue(MAIN_VIEW_PAGER_ADAPTER) as List<Map<String, List<ProductSimple>>>)
            binding.watchedProductsRecyclerView.setupAdapter(watchedProductsCardAdapter, adaptersData.data.getValue(WATCHED_PRODUCTS_CARD_ADAPTER) as List<ProductSimple>, GRID(2, HORIZONTAL))
        }
    }

    private fun setupMainViewPager(productsWithTabs: List<Map<String, List<ProductSimple>>>) {
        val tabsName = mutableListOf<String>()
        val adapters = (0 until 3).map {
            SimpleProductListAdapter().apply {
                tabsName.add(it, productsWithTabs[0].keys.elementAt(it))
                data = productsWithTabs[0].getValue(tabsName[it])
            }
        }
        mainViewPagerAdapter.data = adapters
        with(binding.trendsViewPager) {
            adapter = mainViewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        TabLayoutMediator(binding.tabLayout, binding.trendsViewPager) { tab, position ->
            tab.text = tabsName[position]
        }.attach()
        for (i in 0 until binding.tabLayout.tabCount) {
            val childTab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = childTab.layoutParams as ViewGroup.MarginLayoutParams
            when (i) {
                0 -> p.setMargins(0, 0, 10, 0)
                1 -> p.setMargins(10, 0, 10, 0)
                2 -> p.setMargins(10, 0,0,0)
            }
            childTab.requestLayout()
        }
    }

    companion object {

        private const val EXTRA_ADAPTERS_DATA = "EXTRA_ADAPTERS_DATA"

        fun getNewInstance(adaptersData: AdaptersData): MainFragment {
            return MainFragment().apply {
                arguments = bundleOf(EXTRA_ADAPTERS_DATA to adaptersData)
            }
        }

    }

}