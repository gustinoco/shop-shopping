package br.com.shop.shopping.repository

import com.google.gson.annotations.SerializedName

data class ShoppingRequest(
    @SerializedName("cpf")
    val cpf: String? = null)