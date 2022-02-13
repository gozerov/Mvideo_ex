package ru.gozerov.mvideo_ex.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.github.terrakok.cicerone.Router
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.mvideo_ex.navigation.Screens

class TabsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val router: Router
): ViewModel() {

    private var isInitialized = savedStateHandle.get<Boolean>("isInitialized")

    fun setupNavigation(data: AdaptersData) {
        if (isInitialized == null || isInitialized == false) {
            router.newRootScreen(Screens.mainFragment(data))
            isInitialized = true
            savedStateHandle.set("isInitialized", isInitialized)
        }
    }

    class Factory(
        private val router: Router,
        owner: SavedStateRegistryOwner,
        defaultArgs: Bundle?
    ): AbstractSavedStateViewModelFactory(owner, defaultArgs) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return TabsViewModel(handle, router) as T
        }

    }

}