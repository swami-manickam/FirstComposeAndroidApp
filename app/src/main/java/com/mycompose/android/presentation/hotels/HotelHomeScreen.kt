package com.mycompose.android.presentation.hotels

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mycompose.android.ui.theme.Pink80
import com.mycompose.app.R


@Composable
fun HotelHomeScreen() {


    Column(modifier = Modifier.padding(top = 20.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState(0)),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Spacer(modifier = Modifier.padding(16.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.color_gray))
                    .clickable {

                    }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(id = R.drawable.ic_category_breakfast),
                    contentDescription = "Breakfast",
                    tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.color_gray))
                    .clickable {

                    }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(id = R.drawable.ic_category_nonveg),
                    contentDescription = "Non Veg", tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.color_gray))
                    .clickable {

                    }.padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(id = R.drawable.ic_category_veg),
                    contentDescription = "Fruits", tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.color_gray))
                    .clickable {

                    }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(id = R.drawable.ic_category_salad),
                    contentDescription = "Salad", tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(colorResource(id = R.color.color_gray))
                    .clickable {

                    }
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(90.dp),
                    painter = painterResource(id = R.drawable.ic_category_soft_drink),
                    contentDescription = "Soft Drinks", tint = Color.Unspecified
                )
            }
            Spacer(modifier = Modifier.padding(16.dp))
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.size(10.dp))


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "TOP SELLER",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.background)
                )
                TextButton(onClick = {}) {
                    Text(
                        text = stringResource(id = R.string.see_all),
                        color = colorResource(id = R.color.background)
                    )
                }
            }

            val hotelCategory = listOf(
                HotelCategory(
                    "Briyani Hut",
                    "Bangalore",
                    "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"
                ),
                HotelCategory(
                    "Dosai Walaa",
                    "Chennai",
                    "https://www.thecocktaildb.com/images/media/drink/bry4qh1582751040.jpg"
                ),
                HotelCategory(
                    "Idlly Hut",
                    "Hyderabad",
                    "https://www.thecocktaildb.com/images/media/drink/loezxn1504373874.jpg"
                ),
                HotelCategory(
                    "Five Star Chicken",
                    "Mumbai",
                    "https://www.thecocktaildb.com/images/media/drink/srpxxp1441209622.jpg"
                ),
                HotelCategory(
                    "Chai Bhaai",
                    "Delhi",
                    "https://www.thecocktaildb.com/images/media/drink/tqyrpw1439905311.jpg"
                ),
            )
            HotelList(hotelCategory) {

            }

        }

    }
}


@Composable
fun HotelList(dataList: List<HotelCategory>, onClick: (HotelCategory) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        this.item {
            Spacer(modifier = Modifier.size(10.dp))
        }
        items(dataList) { hotelItem ->
            HotelCardItem(hotelItem) {
                onClick(hotelItem)
            }
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}


@Composable
fun HotelCardItem(category: HotelCategory, onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable {
                onClick()
            }, contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(210.dp)
                .height(280.dp)
        ) {

            androidx.compose.material.Card(
                backgroundColor = MaterialTheme.colors.background,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .align(Alignment.BottomCenter),
                elevation = 4.dp,
                shape = RoundedCornerShape(10.dp)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 25.dp)
                        .padding(6.dp)
                ) {

                    Text(
                        text = category.name,
                        style = MaterialTheme.typography.h3,
                        fontSize = 18.sp,
                    )

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = category.location,
                        style = MaterialTheme.typography.body2,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }
            }

            //Image
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp)
                    .align(Alignment.TopCenter), shape = RoundedCornerShape(20.dp),
                elevation = 6.dp
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        model = category.imageUrl, contentDescription = null
                    )

                    CustomGradient()


                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = category.location,
                            style = MaterialTheme.typography.body1,
                            fontSize = 22.sp, fontWeight = FontWeight.Bold,
                            color = Color.White
                        )

                        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                modifier = Modifier.size(10.dp),
                                painter = painterResource(id = androidx.appcompat.R.drawable.abc_ic_arrow_drop_right_black_24dp),
                                contentDescription = null,
                                tint = Color.White.copy(alpha = 0.7f)
                            )
                            Spacer(modifier = Modifier.size(8.dp))
                            Text(
                                text = category.location,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CustomGradient() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colorStops = arrayOf(
                        Pair(0.40f, Color.Transparent),
                        Pair(1f, Color.Black.copy(alpha = 0.2f))
                    )
                )
            )
    )
}

@Preview
@Composable
fun ShowHotelHomePreview() {
    HotelHomeScreen()
}


data class HotelCategory(val name: String, val location: String, val imageUrl: String)