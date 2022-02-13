package ru.gozerov.mvideo_ex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import ru.gozerov.domain.entity.BonusProgram
import ru.gozerov.domain.usecase.GetBonusProgramsUseCase

class PreLoginViewModel(
    icons: Map<String, Int>
): ViewModel() {

    private val _adapterDataFlow = MutableSharedFlow<List<BonusProgram>>(1, 0, BufferOverflow.DROP_OLDEST)
    val adapterDataFlow: SharedFlow<List<BonusProgram>> = _adapterDataFlow.asSharedFlow()

    init {
        _adapterDataFlow.tryEmit(GetBonusProgramsUseCase(icons).execute())
    }

    class Factory(private val icons: Map<String, Int>): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return PreLoginViewModel(icons) as T
        }

    }

}