package ru.gozerov.mvideo_ex.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.gozerov.domain.entity.AdaptersData
import ru.gozerov.domain.entity.AdaptersDataConverter
import ru.gozerov.domain.usecase.GetAdaptersDataUseCase

class SplashViewModel(
    private val strings: Map<String, String>,
    private val icons: Map<String, Int>
    ): ViewModel() {

    private val _adaptersDataConfigFlow = MutableSharedFlow<AdaptersData>(0, 1, BufferOverflow.DROP_OLDEST)
    val adaptersDataConfigFlow: SharedFlow<AdaptersData> = _adaptersDataConfigFlow.asSharedFlow()

    init {
        emitData()
    }

    private fun emitData() = viewModelScope.launch {
        val data = GetAdaptersDataUseCase(strings, icons).execute()
        delay(500)
        _adaptersDataConfigFlow.emit(AdaptersDataConverter.convert(data))
    }

    class Factory(
        private val factoryStrings: Map<String, String>,
        private val factoryIcons: Map<String, Int>
        ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SplashViewModel(factoryStrings, factoryIcons) as T
        }

    }

}