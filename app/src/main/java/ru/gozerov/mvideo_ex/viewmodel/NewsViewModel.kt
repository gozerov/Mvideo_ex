package ru.gozerov.mvideo_ex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.gozerov.domain.entity.news.SimpleNews
import ru.gozerov.domain.usecase.GetSimpleNewsUseCase
import javax.inject.Inject

class NewsViewModel @Inject constructor (
    private val getSimpleNewsUseCase: GetSimpleNewsUseCase
): ViewModel() {

    private val _data = MutableSharedFlow<List<SimpleNews>>(1,0,BufferOverflow.DROP_OLDEST)
    val data: SharedFlow<List<SimpleNews>> = _data.asSharedFlow()

    init {
        viewModelScope.launch {
            _data.emit(getSimpleNewsUseCase.execute())
        }
    }

    class Factory @Inject constructor (
        private val getSimpleNewsUseCase: GetSimpleNewsUseCase
    ): ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NewsViewModel(getSimpleNewsUseCase) as T
        }
    }

}