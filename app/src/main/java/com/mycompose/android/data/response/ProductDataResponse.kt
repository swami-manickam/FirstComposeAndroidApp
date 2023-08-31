package com.mycompose.android.data.response

import com.google.gson.annotations.SerializedName

data class ProductDataResponse(
    @SerializedName("drinks") val results: List<ProductPayload>
)


data class ProductPayload(
    @SerializedName("idDrink") val idDrink: Int?,
    @SerializedName("strDrink") val strDrink: String,
    @SerializedName("strAlcoholic") val strAlcoholic: String,
    @SerializedName("strCategory") val strCategory: String,
    @SerializedName("strDrinkThumb") val strDrinkThumb: String,
    @SerializedName("strInstructions") val strInstructions: String,
    @SerializedName("dateModified") val dateModified: String?
)