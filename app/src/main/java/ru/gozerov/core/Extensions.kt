package ru.gozerov.core

import android.content.Context
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import ru.gozerov.mvideo_ex.di.AppComponent
import ru.gozerov.mvideo_ex.singleton.App


fun <T> Fragment.sendResult(key: String, result: T) {
    this.parentFragmentManager.setFragmentResult("RESULT_KEY", bundleOf(key to result))
}

val Context.appComponent: AppComponent
    get() = when(this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }