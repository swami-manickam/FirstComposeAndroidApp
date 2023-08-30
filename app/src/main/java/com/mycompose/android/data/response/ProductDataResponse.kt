package com.mycompose.android.data.response

import com.google.gson.annotations.SerializedName

data class ProductDataResponse(
    @SerializedName("drinks") val results: List<ProductPayload>
)


data class ProductPayload(
    @SerializedName("idDrink") val id: Int?,
    @SerializedName("strDrink") val name: String,
    @SerializedName("strAlcoholic") val alcoholic: String,
    @SerializedName("strCategory") val category: String,
    @SerializedName("strDrinkThumb") val image: String,
    @SerializedName("strInstructions") val info: String,
    @SerializedName("dateModified") val dateModified: String?
)