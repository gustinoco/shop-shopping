package br.com.shop.shopping.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.shop.shopping.ShoppingModuleSession
import br.com.shop.shopping.repository.ShoppingRepository

class ShoppingViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        ShoppingViewModel(
            ShoppingRepository(ShoppingModuleSession.dependencies.shopNetwork)
        ) as T
}
