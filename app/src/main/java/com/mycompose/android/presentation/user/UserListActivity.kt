package com.mycompose.android.presentation.user

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.data.response.base.AppResponse
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.ui.theme.FirstComposeAppTheme
import retrofit2.Response

class UserListActivity : BaseActivity() {

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstComposeAppTheme {
                Surface(color = MaterialTheme.colorScheme.primary) {
                    /*fetchAllRecords()*/
                   /* userViewModel.getProductDetails()*/
                    ProductScreen(userViewModel.viewState, userViewModel)
                }
            }

        }
    }

    @SuppressLint("NewApi")
    @Composable
    fun ProductScreen(state: ComposeState, userViewModel: UserViewModel) {

        LaunchedEffect(Unit) {
            userViewModel.getProductDetails()
        }

        userViewModel.viewState.data?.let {
            loadProducts(productPayload = state.data as List<ProductPayload>?)
        }

        if (userViewModel.viewState.isLoading) {
            /*CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )*/
        }

        userViewModel.viewState.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }
    }

    @Composable
    fun loadProducts(productPayload: List<ProductPayload>?) = LazyColumn {
        items(productPayload!!) { payload ->
            showProductList(product = payload)
        }
    }

    @Composable
    fun showProductList(product: ProductPayload?) {

        Row(modifier = Modifier.padding(4.dp)) {
            Column {
                Image(
                    painter = rememberAsyncImagePainter(product?.image), "",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(4.dp)
                        .clip(RectangleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = product?.category!!,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = product.alcoholic, color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = product.name, color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }


    @Composable
    private fun fetchAllRecords() {
        /*userViewModel.userData.observe(this) {
            when (it.status) {
                AppResponse.ResponseStatus.SUCCESS -> {
                    val payloadList = it.data?.results
                    payloadList?.let {
                        // loadProducts(productPayload = payloadList)
                    } ?: run {

                    }
                }
                AppResponse.ResponseStatus.ERROR -> {

                }
                else -> {}
            }
        }*/
    }


}