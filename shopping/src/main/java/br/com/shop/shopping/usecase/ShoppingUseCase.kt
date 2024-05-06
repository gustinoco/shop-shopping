package br.com.shop.shopping.usecase

import br.com.shop.shopping.model.ShoppingModel
import br.com.shop.shopping.repository.ShoppingMapper
import br.com.shop.shopping.repository.ShoppingRepository
import br.com.shop.shopping.repository.ShoppingRequest

class ShoppingUseCase(private val repository: ShoppingRepository) {

    suspend fun loadCartApi(cpf: String): MutableList<ShoppingModel> {
        val response = repository.postLogin(ShoppingRequest(cpf))
        return ShoppingMapper.convertResponseToModel(response)
    }
}