package com.mycompose.android.presentation.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mycompose.android.data.repo.ComposeRepo
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ComposeRepo
) : BaseViewModel() {

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