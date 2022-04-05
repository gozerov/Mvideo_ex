package ru.gozerov.mvideo_ex.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.gozerov.mvideo_ex.singleton.App
import ru.gozerov.mvideo_ex.splash.SplashActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(app: App)
    fun inject(activity: SplashActivity)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder

    }
}