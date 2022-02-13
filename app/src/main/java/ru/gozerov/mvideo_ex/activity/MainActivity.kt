package ru.gozerov.mvideo_ex.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.mvideo_ex.R
import ru.gozerov.mvideo_ex.navigation.Screens
import ru.gozerov.mvideo_ex.singleton.App

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var navigator: AppNavigator

    override fun onPause() {
        App.INSTANCE.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onResumeFragments() {
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
        super.onResumeFragments()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = AppNavigator(this, R.id.parentFragmentContainer, supportFragmentManager)
        val data = intent.getSerializableExtra("ADAPTERS_DATA") as AdaptersData
        if (savedInstanceState==null)
            App.INSTANCE.router.newRootScreen(Screens.tabsFragment(data))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("key", false)
        super.onSaveInstanceState(outState)
    }

}