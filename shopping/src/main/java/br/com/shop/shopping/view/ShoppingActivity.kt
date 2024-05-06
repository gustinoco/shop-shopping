package br.com.shop.shopping.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.shop.commons.ShopRoutes
import br.com.shop.shopping.ShoppingModuleSession
import br.com.shop.shopping.commons.ShoppingAnalyticsConstants
import br.com.shop.shopping.databinding.ShoppingActivityMainBinding
import br.com.shop.shopping.viewmodel.ShoppingViewEvent
import br.com.shop.shopping.viewmodel.ShoppingViewModel
import br.com.shop.shopping.viewmodel.ShoppingViewModelFactory
import br.com.shop.shopping.viewmodel.ShoppingViewState

internal class ShoppingActivity: AppCompatActivity() {

    private lateinit var binding: ShoppingActivityMainBinding

    private val viewModel by lazy {
        ViewModelProvider(this, ShoppingViewModelFactory())[ShoppingViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setTheme(ShoploginSession.theme)
        binding = ShoppingActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        initObservers()
    }

    fun initObservers() {
        viewModel.viewState.observe(this) {
            when (it) {
                is ShoppingViewState.Error -> Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                is ShoppingViewState.Success -> {
                    val productAdapter = ShoppingAdapter(
                        this,
                        it.shoppingModel,
                        ShoppingModuleSession.dependencies.shopCache,
                        ShoppingModuleSession.dependencies.shopAnalytics
                    )
                    binding.listItensCart.adapter = productAdapter
                }
            }
        }

        viewModel.viewEvent.observe(this) {
            when (it) {
                is ShoppingViewEvent.Loading -> if (it.show) {
                    binding.nestedScroll.visibility = android.view.View.GONE
                    binding.progressbar.visibility = android.view.View.VISIBLE
                } else {
                    binding.progressbar.visibility = android.view.View.GONE
                    binding.nestedScroll.visibility = android.view.View.VISIBLE
                }
            }
        }
    }

    fun init() {
        ShoppingModuleSession.dependencies.shopAnalytics?.trackScreen(ShoppingAnalyticsConstants.TrackScreenShooping)
        viewModel.init(ShoppingModuleSession.keyCart)
        binding.listItensCart.layoutManager = LinearLayoutManager(this)
        binding.btnPayment.setOnClickListener {
            ShoppingModuleSession.dependencies.shopNavigate?.navigate(this, ShopRoutes.Cart.route)
        }
    }
}