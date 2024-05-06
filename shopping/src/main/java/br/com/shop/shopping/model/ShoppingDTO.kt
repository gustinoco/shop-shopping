package br.com.shop.shopping.model

import com.google.gson.annotations.SerializedName

data class ShoppingDTO(
    @SerializedName("idProduct")
    val idProduct: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("stock")
    val stock: Int
)
