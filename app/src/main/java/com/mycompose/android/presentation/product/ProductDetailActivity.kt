package com.mycompose.android.presentation.product

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.ui.theme.FirstComposeAppTheme

class ProductDetailActivity : BaseActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstComposeAppTheme {

                /*Column {*/
                val scrollBehavior =
                    TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.background
                            ),
                            title = { Text(text = "My Products") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.background
                                    )
                                }
                            },
                            scrollBehavior = scrollBehavior
                        )
                    }) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        ProductScreen()
                    }
                }
                /*}*/
            }
        }
    }


    /*Methode 2*/
    @SuppressLint("NewApi")
    @Composable
    fun ProductScreen() {
        val userdata = productViewModel.userList.observeAsState(null)

        LaunchedEffect(Unit) {
            productViewModel.getProductDetails()
        }

        if (userdata.value == null)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }


        if (userdata.value != null)
            LoadGrid(productPayload = userdata.value)

    }


    @Composable
    fun LoadGrid(productPayload: List<ProductPayload>?) =
        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
            items(productPayload!!) { payload ->
                ShowProductList(payload)
            }
        }


    @Composable
    fun LoadProducts(productPayload: List<ProductPayload>?) = LazyColumn {
        items(productPayload!!) { payload ->
            ShowProductList(product = payload)
        }
    }

    @Composable
    fun ShowProductList(product: ProductPayload) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 1.dp,
            modifier = Modifier.padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background, RectangleShape)
                    .padding(8.dp)
            ) {
                Column {
                    AsyncImage(
                        model = product.strDrinkThumb,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                border = ButtonDefaults.outlinedButtonBorder,
                                shape = RoundedCornerShape(3.dp)
                            )
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = product.strCategory,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(1.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = product.strAlcoholic,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = product.strDrink,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    /*Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(20.dp)
                            .background(Color.Cyan),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Text(
                            text = "How many cars are in the garage",
                            textAlign = TextAlign.Center
                        )
                    }*/
                }
            }
        }

    }


}