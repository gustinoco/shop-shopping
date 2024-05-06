package br.com.shop.shopping

import br.com.shop.commons.ShopAnalytics
import br.com.shop.commons.ShopCache
import br.com.shop.commons.ShopDesignSystem
import br.com.shop.commons.ShopNavigate
import br.com.shop.commons.ShopNetwork

data class ShoppingModuleDependencies(
    val shopNetwork: ShopNetwork? = null,
    val shopAnalytics: ShopAnalytics? = null,
    val shopDesignSystem: ShopDesignSystem? = null,
    val shopNavigate: ShopNavigate? = null,
    val shopCache: ShopCache? = null
)