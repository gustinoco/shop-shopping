package br.com.shop.shopping.viewmodel

import br.com.shop.shopping.model.ShoppingModel

sealed class ShoppingViewState {

    data class Success(val shoppingModel: MutableList<ShoppingModel>) : ShoppingViewState()
    data class Error(val message: String) : ShoppingViewState()

}