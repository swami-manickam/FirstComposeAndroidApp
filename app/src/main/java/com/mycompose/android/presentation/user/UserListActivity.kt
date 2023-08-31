package com.mycompose.android.presentation.user

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.data.response.base.AppResponse
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.ui.theme.FirstComposeAppTheme

class UserListActivity : BaseActivity() {

    private val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstComposeAppTheme {
                Surface(color = MaterialTheme.colorScheme.primary) {
                    /*Methode 1*/
                    FetchAllRecords()
                   /* userViewModel.getProductDetails()*/
                    /*Methode 2*/
//                    ProductScreen(userViewModel.viewState, userViewModel)
                }
            }

        }

    }

    /*Methode 2*/
    @SuppressLint("NewApi")
    @Composable
    fun ProductScreen(state: ComposeState, userViewModel: UserViewModel) {
        val userdata = userViewModel.userList.observeAsState(null)

        LaunchedEffect(Unit) {
            userViewModel.getProductDetails()
        }

        if(userdata.value != null)
        LoadProducts(productPayload = userdata.value)

//        userViewModel.viewState.data?.let {
//            LoadProducts(productPayload = state.data as List<ProductPayload>?)
//        }

        /*if (userViewModel.viewState.isLoading) {
            *//*CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )*//*
        }

        userViewModel.viewState.error?.let { error ->
            Text(
                text = error,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }*/
    }

    @Composable
    fun LoadProducts(productPayload: List<ProductPayload>?) = LazyColumn {
        items(productPayload!!) { payload ->
            ShowProductList(product = payload)
        }
    }

    @Composable
    fun ShowProductList(product: ProductPayload) {

        Row(modifier = Modifier.padding(4.dp)) {
            Column {
                /*GlideImage(
                    model = product.strDrinkThumb,
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .padding(4.dp)
                        .clip(RectangleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape) )*/
                /*AsyncImage(
                    model = product.strDrinkThumb,
                    contentDescription = null,
                    modifier = Modifier
                        .size(250.dp)
                        .padding(4.dp)
                        .clip(RectangleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape)
                )*/

               /*Image(
                    painter = rememberAsyncImagePainter(product.strDrinkThumb), "",
                    *//*modifier = Modifier
                        .size(250.dp)
                        .padding(4.dp)
                        .clip(RectangleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape)*//*
                )*/
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = product.strCategory,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.width(1.dp))
                Text(
                    text = product.strAlcoholic,
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    text = product.strDrink,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }

    /*Methode 1*/
@Composable
    fun FetchAllRecords() {
        userViewModel.userData.observe(this) {
            when (it.status) {
                AppResponse.ResponseStatus.SUCCESS -> {
                    //Toast.makeText(this, it.data?.results.toString(),Toast.LENGTH_SHORT).show()
                    userViewModel._userList.value = it.data?.results
                }

                AppResponse.ResponseStatus.ERROR ->{}

                AppResponse.ResponseStatus.LOADING ->{}
            }
        }

    val userdata = userViewModel.userList.observeAsState(null)

    if(userdata.value != null)
        LoadProducts(productPayload = userdata.value)
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