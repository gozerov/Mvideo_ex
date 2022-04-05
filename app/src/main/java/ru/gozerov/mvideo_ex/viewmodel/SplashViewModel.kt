package ru.gozerov.mvideo_ex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.domain.entity.AdaptersDataConverter
import ru.gozerov.domain.entity.CardRes
import ru.gozerov.domain.usecase.GetAdaptersDataUseCase

class SplashViewModel(
    private val cardRes: CardRes,
    private val getAdaptersDataUseCase: GetAdaptersDataUseCase
    ): ViewModel() {

    private val _adaptersDataConfigFlow = MutableSharedFlow<AdaptersData>(0, 1, BufferOverflow.DROP_OLDEST)
    val adaptersDataConfigFlow: SharedFlow<AdaptersData> = _adaptersDataConfigFlow.asSharedFlow()

    init {
        emitData()
    }

    private fun emitData() = viewModelScope.launch {
        val data = getAdaptersDataUseCase.execute(cardRes)
        delay(500)
        _adaptersDataConfigFlow.emit(AdaptersDataConverter.convert(data))
    }

    class SplashVMFactory @AssistedInject constructor (
        @Assisted("cardRes") private val factoryCardRes: CardRes,
        private val getAdaptersDataUseCase: GetAdaptersDataUseCase
        ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashViewModel(cardRes = factoryCardRes, getAdaptersDataUseCase = getAdaptersDataUseCase) as T
        }

        @AssistedFactory
        interface Factory {

            fun create(@Assisted("cardRes") factoryCardRes: CardRes): SplashVMFactory

        }

    }

}