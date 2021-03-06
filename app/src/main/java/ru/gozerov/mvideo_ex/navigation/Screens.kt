package ru.gozerov.mvideo_ex.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.mvideo_ex.fragment.*

object Screens {

    fun tabsFragment(adaptersData: AdaptersData) = FragmentScreen { TabsFragment.getNewInstance(adaptersData) }
    fun mainFragment(adaptersData: AdaptersData) = FragmentScreen { MainFragment.getNewInstance(adaptersData) }
    fun preLoginFragment() = FragmentScreen { PreLoginFragment() }
    fun loginByPhoneFragment() = FragmentScreen { LoginByPhoneFragment() }
    fun newsFragment() = FragmentScreen { NewsFragment() }

}