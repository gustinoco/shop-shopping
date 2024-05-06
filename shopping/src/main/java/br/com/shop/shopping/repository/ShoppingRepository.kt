package br.com.shop.shopping.repository

import br.com.shop.commons.ShopNetwork
import br.com.shop.shopping.commons.ShoppingAnalyticsConstants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import br.com.shop.shopping.model.ShoppingDTO

class ShoppingRepository(private val api: ShopNetwork?) {

    suspend fun postLogin(
        loginRequest: ShoppingRequest
    ): List<ShoppingDTO> {
        val response = withContext(Dispatchers.Default) {
            api?.postNetwork(
                ShoppingAnalyticsConstants.urlEndpointShopping,
                loginRequest,
                ShoppingResponse::class
            ) as ShoppingResponse
        }
        return response.data
    }
}