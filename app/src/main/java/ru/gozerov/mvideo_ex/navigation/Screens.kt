package ru.gozerov.mvideo_ex.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.mvideo_ex.fragment.LoginByPhoneFragment
import ru.gozerov.mvideo_ex.fragment.MainFragment
import ru.gozerov.mvideo_ex.fragment.PreLoginFragment
import ru.gozerov.mvideo_ex.fragment.TabsFragment

object Screens {

    fun tabsFragment(adaptersData: AdaptersData) = FragmentScreen { TabsFragment.getNewInstance(adaptersData) }
    fun mainFragment(adaptersData: AdaptersData) = FragmentScreen { MainFragment.getNewInstance(adaptersData) }
    fun preLoginFragment() = FragmentScreen { PreLoginFragment() }
    fun loginByPhoneFragment() = FragmentScreen { LoginByPhoneFragment() }

}