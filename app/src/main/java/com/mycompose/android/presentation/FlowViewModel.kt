package com.mycompose.android.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch


@HiltViewModel
class FlowViewModel : ViewModel() {


    private val _liveData = MutableLiveData("Welcome To Jetpack compose")
    val liveData: LiveData<String> = _liveData

    private val _stateFlow = MutableStateFlow("Welcome To Jetpack compos")
    val stateFlow = _stateFlow.asStateFlow()

    private val _sharedFlow = MutableSharedFlow<String>()
    val sharedFlow = _sharedFlow.asSharedFlow()


    fun triggerLiveData() {
        _liveData.value = "LiveData Update"
    }


    fun triggerStateFlow() {
        _stateFlow.value = "StateFlow Update"
    }

    fun triggerFlow(): Flow<Int> {
        return flow {
            repeat(5) {
                emit(it + 1)
                delay(1000L)
            }
        }
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlow.emit("SharedFlow")
        }
    }


}