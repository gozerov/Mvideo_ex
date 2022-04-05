package ru.gozerov.mvideo_ex.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import ru.gozerov.domain.entity.CardRes
import ru.gozerov.mvideo_ex.R
import ru.gozerov.mvideo_ex.activity.MainActivity
import ru.gozerov.mvideo_ex.singleton.appComponent
import ru.gozerov.mvideo_ex.viewmodel.SplashViewModel
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
class SplashActivity: AppCompatActivity() {

    @Inject
    lateinit var factory: SplashViewModel.SplashVMFactory.Factory

    private val viewModel: SplashViewModel by viewModels {
        factory.create (
            CardRes(
                listOf(
                    mapOf(R.string.login to R.drawable.ic_baseline_person_outline_24),
                    mapOf(R.string.about_orders to R.drawable.ic_baseline_drafts_24),
                    mapOf(R.string.take_orders_from_shop to R.drawable.ic_baseline_house_24),
                    mapOf(R.string.personals to R.drawable.ic_baseline_card_giftcard_24),
                    mapOf(R.string.discounts to R.drawable.ic_baseline_timeline_24)
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.appComponent.inject(this@SplashActivity)
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