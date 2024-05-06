package br.com.shop.shopping.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingModel(
    val idProduct: String,
    val value: String,
    val description: String,
    val stock: Int
) : Parcelable