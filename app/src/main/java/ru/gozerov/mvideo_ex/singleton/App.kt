package ru.gozerov.mvideo_ex.singleton

import android.app.Application
import android.content.Context
import com.github.terrakok.cicerone.Cicerone
import com.google.firebase.FirebaseApp
import ru.gozerov.mvideo_ex.di.AppComponent
import ru.gozerov.mvideo_ex.di.DaggerAppComponent

class App : Application() {
    private val cicerone = Cicerone.create()
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent
            .builder()
            .context(applicationContext)
            .build()
        appComponent.inject(this)
        super.onCreate()
        FirebaseApp.initializeApp(this)
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}