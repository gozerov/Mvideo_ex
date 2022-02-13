package ru.gozerov.mvideo_ex.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import ru.gozerov.domain.usecase.TopCards.ABOUT_ORDERS
import ru.gozerov.domain.usecase.TopCards.DISCOUNTS
import ru.gozerov.domain.usecase.TopCards.LOGIN
import ru.gozerov.domain.usecase.TopCards.PERSONALS
import ru.gozerov.domain.usecase.TopCards.TAKE_ORDERS_FROM_SHOP
import ru.gozerov.mvideo_ex.R
import ru.gozerov.mvideo_ex.activity.MainActivity
import ru.gozerov.mvideo_ex.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels {
        SplashViewModel.Factory(
            mapOf(
                LOGIN to getString(R.string.login),
                ABOUT_ORDERS to getString(R.string.about_orders),
                TAKE_ORDERS_FROM_SHOP to getString(R.string.take_orders_from_shop),
                PERSONALS to getString(R.string.personals),
                DISCOUNTS to getString(R.string.discounts)
            ),
            mapOf(
                LOGIN to R.drawable.ic_baseline_person_outline_24,
                ABOUT_ORDERS to R.drawable.ic_baseline_drafts_24,
                TAKE_ORDERS_FROM_SHOP to R.drawable.ic_baseline_house_24,
                PERSONALS to R.drawable.ic_baseline_card_giftcard_24,
                DISCOUNTS to R.drawable.ic_baseline_timeline_24
            ),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        lifecycleScope.launchWhenStarted {
            viewModel.adaptersDataConfigFlow.collect { adaptersData ->
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.putExtra("ADAPTERS_DATA", adaptersData)
                
                startActivity(intent)
                finish()
            }
        }
    }

}