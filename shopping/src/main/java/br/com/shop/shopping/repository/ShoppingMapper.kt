package br.com.shop.shopping.repository

import br.com.shop.shopping.model.ShoppingDTO
import br.com.shop.shopping.model.ShoppingModel

object ShoppingMapper {

    fun convertResponseToModel(
        response: List<ShoppingDTO>,
    ): MutableList<ShoppingModel> {
        val list = mutableListOf<ShoppingModel>()
        response.forEach() {
            list.add(
                ShoppingModel(
                    idProduct = it.idProduct,
                    value = it.value,
                    description = it.description,
                    stock = it.stock
                )
            )
        }
        return list
    }
}
