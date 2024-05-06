package br.com.shop.shopping.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.shop.commons.ShopAnalytics
import br.com.shop.commons.ShopCache
import com.google.android.material.snackbar.Snackbar
import br.com.shop.shopping.ShoppingModuleSession
import br.com.shop.shopping.commons.ShoppingAnalyticsConstants
import br.com.shop.shopping.databinding.ShoppingItemListItensBinding
import br.com.shop.shopping.model.ShoppingModel
import br.com.shop.shopping.repository.ShoppingResponse

class ShoppingAdapter(
    private var mContext: Context,
    private var productList: MutableList<ShoppingModel>,
    private var shopCache: ShopCache?,
    private var shopAnalytics: ShopAnalytics?
) : RecyclerView.Adapter<ShoppingAdapter.CardViewHolder>() {

    inner class CardViewHolder(var view: ShoppingItemListItensBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ShoppingItemListItensBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return CardViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val product = productList[position]
        val view = holder.view

        view.btnAddCart.setOnClickListener {
            shopAnalytics?.trackAction(ShoppingAnalyticsConstants.TrackScreenShooping, "Adicionando produto: " + product.description)
            Snackbar.make(it, "${product.description} adicionado no carrinho", Snackbar.LENGTH_SHORT).show()
            var cart = shopCache?.getCache(ShoppingModuleSession.keyCart, ShoppingResponse::class)
            if(cart is MutableList<*>) {
                cart = cart as MutableList<ShoppingModel>
                cart.add(product)
            } else {
                cart = mutableListOf(product)
            }
            shopCache?.putCache(ShoppingModuleSession.keyCart, cart)
        }
        view.txtProductDescription.text = product.description
        view.txtValueDescription.text = product.value
        view.txtStockDescription.text = product.stock.toString()
    }
}