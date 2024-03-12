package com.mycompose.android.presentation.product

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.mycompose.android.data.response.ProductPayload
import com.mycompose.android.data.response.base.AppResponse
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.presentation.navigation.BottomNav
import com.mycompose.android.presentation.navigation.NavAppScaffold
import com.mycompose.android.presentation.navigation.NavigationGraph
import com.mycompose.android.ui.theme.FirstComposeAppTheme

class ProductListActivity : BaseActivity() {

    private val productViewModel: ProductViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            FirstComposeAppTheme {

                //
                Surface(color = MaterialTheme.colorScheme.background) {

                    Scaffold(/*topBar = {
                        TopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                titleContentColor = MaterialTheme.colorScheme.background
                            ),
                            title = { Text(text = "Product Lists") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = "",
                                        tint = MaterialTheme.colorScheme.background
                                    )
                                }
                            },
                        )
                    }*/
                    ) { innerPadding ->
                        Column(Modifier.padding(innerPadding)) {
                            /*FetchAllRecords()*/
                            /*NavScreenView()*/
                            NavAppScaffold()
                        }
                    }

                }
                //
            }

        }

    }


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    fun NavScreenView(){
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNav(navController = navController) }) {

            NavigationGraph(navController = navController)
        }
    }


    /*Methode 1*/
    @Composable
    fun FetchAllRecords() {

        productViewModel.userData.observe(this) {
            when (it.status) {
                AppResponse.ResponseStatus.SUCCESS -> {
                    productViewModel._userList.value = it.data?.results
                }

                AppResponse.ResponseStatus.ERROR -> {

                }

                AppResponse.ResponseStatus.LOADING -> {

                }
            }
        }

        val userdata = productViewModel.userList.observeAsState(null)
        if (userdata.value == null)
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        else if (userdata.value != null)
            LoadProducts(productPayload = userdata.value)
    }


    @Composable
    fun LoadProducts(productPayload: List<ProductPayload>?) = LazyColumn {
        items(productPayload!!) { payload ->
            ShowProductList(product = payload)
        }
    }

    @Composable
    fun ShowProductList(product: ProductPayload) {

        val context = LocalContext.current

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
                    .clickable(onClick = {
                        context.startActivity(Intent(context, ProductDetailActivity::class.java))
                    })
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

/*    @Composable
    fun Load*/

    /*Image(
                     painter = rememberAsyncImagePainter(product.strDrinkThumb), "",
                     *//*modifier = Modifier
                        .size(250.dp)
                        .padding(4.dp)
                        .clip(RectangleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, RectangleShape)*//*
                )*/

}