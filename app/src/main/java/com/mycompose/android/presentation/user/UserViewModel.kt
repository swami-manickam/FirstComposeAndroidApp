package com.mycompose.android.presentation.user

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mycompose.android.data.repo.ComposeRepo
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.data.response.base.AppResponse
import com.mycompose.android.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: ComposeRepo
) : BaseViewModel() {


     val _userList = MutableLiveData<List<ProductPayload>>()
     val userList: MutableLiveData<List<ProductPayload>> get() = _userList
    /*Methode 1*/
    val userData = repository.getAllProducts()


    var viewState: ComposeState by mutableStateOf(ComposeState())
    /*Methode 2*/
    fun getProductDetails() {
        viewModelScope.launch {
            try {

                val response = repository.getAllProductsList()
                response.let { _userList.value = it.data?.results }
                /*viewState = viewState.copy(isLoading = true, error = null)
                repository.getAllProducts()?.let { response ->
                    val result = response.value
                    when (result?.status) {
                        AppResponse.ResponseStatus.SUCCESS -> {
                            viewState = viewState.copy(
                                data = result.data?.results,
                                isLoading = false,
                                error = null
                            )
                        }
                        AppResponse.ResponseStatus.ERROR -> {
                            viewState = viewState.copy(
                                data = null,
                                isLoading = false,
                                error = result.message
                            )
                        }
                        else -> {}
                    }
                }*/
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