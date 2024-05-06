package br.com.shop.shopping

import android.content.Context
import android.content.Intent
import br.com.shop.shopping.view.ShoppingActivity
import kotlin.properties.Delegates

class ShoppingLoginInitializer private constructor(builder: Builder) {

    init {
        ShoppingModuleSession.dependencies = builder.shoppingModuleDependencies
        ShoppingModuleSession.theme = builder.theme
        ShoppingModuleSession.keyCart = builder.keyCart
        startModuleActivity(builder.context)
    }

    private fun startModuleActivity(context: Context) {
        context.startActivity(
            Intent(context, ShoppingActivity::class.java)
        )
    }

    class Builder {
        internal var context: Context by Delegates.notNull()
        internal var theme: Int by Delegates.notNull()
        internal var keyCart: String by Delegates.notNull()
        internal var shoppingModuleDependencies: ShoppingModuleDependencies by Delegates.notNull()

        fun setContext(context: Context) = apply {
            this.context = context
        }

        fun setTheme(theme: Int) = apply {
            this.theme = theme
        }

        fun setKeyCart(keyCart: String) = apply {
            this.keyCart = keyCart
        }

        fun setLoginModuleDependencies(shoppingModuleDependencies: ShoppingModuleDependencies) = apply {
            this.shoppingModuleDependencies = shoppingModuleDependencies
        }

        fun build() {
            ShoppingLoginInitializer(this)
        }
    }
}
