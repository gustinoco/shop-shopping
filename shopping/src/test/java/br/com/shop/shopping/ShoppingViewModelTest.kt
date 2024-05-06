package br.com.shop.shopping
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.shop.commons.ShopNetwork
import br.com.shop.shopping.model.ShoppingDTO
import br.com.shop.shopping.model.ShoppingModel
import br.com.shop.shopping.repository.ShoppingRepository
import br.com.shop.shopping.repository.ShoppingResponse
import br.com.shop.shopping.usecase.ShoppingUseCase
import br.com.shop.shopping.utils.CoroutineTestExtension
import br.com.shop.shopping.utils.MainDispatcherRule
import br.com.shop.shopping.viewmodel.ShoppingViewEvent
import br.com.shop.shopping.viewmodel.ShoppingViewModel
import br.com.shop.shopping.viewmodel.ShoppingViewState
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Rule
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import io.mockk.coEvery
import kotlinx.coroutines.delay

@ExtendWith(CoroutineTestExtension::class)
class ShoppingViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ShoppingViewModel

    @MockK
    private lateinit var useCase: ShoppingUseCase

    @MockK
    private lateinit var shopNetwork: ShopNetwork

    private lateinit var repository: ShoppingRepository

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
        repository = ShoppingRepository(shopNetwork)
        viewModel = ShoppingViewModel(repository)
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    @Test
    fun `validate state sucess loading itens shopping`() = runBlockingTest {
        coEvery {
            shopNetwork.postNetwork(any(), any(), any())
        } returns ShoppingResponse(listOf(ShoppingDTO("1", "100", "teste", 10)))

        coEvery {
            useCase.loadCartApi(any())
        } returns mutableListOf(ShoppingModel("1", "100", "teste", 10))


        viewModel.init("")
        delay(100)
        assert(viewModel.viewEvent.value is ShoppingViewEvent.Loading)
        assert(viewModel.viewState.value is ShoppingViewState.Success)
    }

    @Test
    fun `validate state false cart pending state`() = runBlockingTest {
        coEvery {
            useCase.loadCartApi(any())
        } returns mutableListOf(ShoppingModel("1", "100", "teste", 10))
        viewModel.init("")
        assert(viewModel.viewState.value is ShoppingViewState.Error)
    }
}
