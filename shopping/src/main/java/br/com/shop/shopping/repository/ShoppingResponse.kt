package br.com.shop.shopping.repository

import com.google.gson.annotations.SerializedName
import br.com.shop.shopping.model.ShoppingDTO

data class ShoppingResponse(
    @SerializedName("data")
    val data: List<ShoppingDTO>
)