package br.com.shop.shopping.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import br.com.shop.shopping.usecase.ShoppingUseCase
import br.com.shop.shopping.repository.ShoppingRepository

class ShoppingViewModel(repository: ShoppingRepository) : ViewModel() {

    private val state: MutableLiveData<ShoppingViewState> = MutableLiveData()
    private val event: MutableLiveData<ShoppingViewEvent> = MutableLiveData()
    val viewState: LiveData<ShoppingViewState> = state
    val viewEvent: LiveData<ShoppingViewEvent> = event

    private var shoppingUseCase: ShoppingUseCase

    private val viewModelJob = SupervisorJob()
    private val coroutineContext = Dispatchers.Main + viewModelJob

    init {
        shoppingUseCase = ShoppingUseCase(repository)
    }

    fun init(cpf: String) {

        CoroutineScope(coroutineContext).launch {

            try {
                event.value = ShoppingViewEvent.Loading(true)
                val response = shoppingUseCase.loadCartApi(cpf)
                event.value = ShoppingViewEvent.Loading(false)
                state.value = ShoppingViewState.Success(response)
            } catch (e: Exception) {
                event.value = ShoppingViewEvent.Loading(false)
                state.value = ShoppingViewState.Error(e.message ?: "Erro desconhecido")
            }
        }
    }
}