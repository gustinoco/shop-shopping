package br.com.shop.shopping

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.shop.commons.ShopAnalytics
import br.com.shop.commons.ShopCache
import br.com.shop.shopping.utils.CoroutineTestExtension
import br.com.shop.shopping.utils.MainDispatcherRule
import br.com.shop.shopping.view.ShoppingAdapter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
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

@ExtendWith(CoroutineTestExtension::class)
class ShoppingAdapterTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var shoppingAdapter: ShoppingAdapter

    private val mContextMock = mockk<Context>(relaxed = true)

    @MockK
    lateinit var shopCache: ShopCache

    @MockK
    lateinit var shopAnalytics: ShopAnalytics

    @BeforeEach
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this)
        shoppingAdapter = ShoppingAdapter(mContextMock, mutableListOf(), shopCache, shopAnalytics)
    }

    @AfterEach
    fun afterEach() {
        Dispatchers.resetMain()
    }

    @Test
    fun `validate adapter`() = runBlockingTest {
        shoppingAdapter.getItemId(0)
    }
}