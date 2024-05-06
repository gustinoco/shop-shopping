package br.com.shop.shopping.viewmodel

sealed class ShoppingViewEvent {

    data class Loading(val show: Boolean) : ShoppingViewEvent()

}