package com.mycompose.android.presentation.product

import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mycompose.android.data.repo.ComposeRepo
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ComposeRepo
) : BaseViewModel() {


    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        viewModelScope.launch {
            delay(3000L)
            _loading.emit(false)
        }
    }

    val _userList = MutableLiveData<List<ProductPayload>>()
    val userList: MutableLiveData<List<ProductPayload>> get() = _userList

    /*Methode 1*/
    val userData = repository.getAllProducts()

    /*Methode 2*/
    fun getProductDetails() {
        viewModelScope.launch {
            try {
                val response = repository.getAllProductsList()
                response.let { _userList.value = it.data?.results }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



}


data class ComposeState(
    val data: Any? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)