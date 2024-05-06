package br.com.shop.shopping

import kotlin.properties.Delegates

object ShoppingModuleSession {

    var dependencies: ShoppingModuleDependencies by Delegates.notNull()
    var theme: Int by Delegates.notNull()
    var keyCart: String by Delegates.notNull()

}