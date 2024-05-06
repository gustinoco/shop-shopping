package shopcarrinho.sample.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.shop.commons.ShopAnalytics
import br.com.shop.commons.ShopCache
import br.com.shop.commons.ShopDesignSystem
import br.com.shop.commons.ShopNavigate
import br.com.shop.commons.ShopNetwork
import com.google.gson.Gson
import kotlinx.coroutines.delay
import br.com.shop.shopping.ShoppingModuleDependencies
import br.com.shop.shopping.ShoppingLoginInitializer
import br.com.shop.shopping.model.ShoppingModel
import br.com.shop.shopping.repository.ShoppingResponse
import shopping.sample.R
import shopping.sample.databinding.ActivitySampleBinding
import java.io.Serializable

class SampleActivity : AppCompatActivity(), ShopAnalytics, ShopDesignSystem, ShopNetwork, ShopNavigate, ShopCache {

    private lateinit var binding: ActivitySampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startModule()
    }

    private fun startModule() {
        val dependencies = ShoppingModuleDependencies(
            this,
            this,
            this,
            this,
            this
        )

        binding.btnGoToModule.setOnClickListener {
            ShoppingLoginInitializer.Builder()
                .setContext(this)
                .setTheme(0)
                .setKeyCart("1234_cart")
                .setLoginModuleDependencies(dependencies)
                .build()
        }
    }

    override fun trackAction(screenName: String, action: String) {
        Log.d(screenName, action)
    }

    override fun trackScreen(screenName: String) {
        Log.d(screenName, screenName)
    }

    override fun primaryColor(): Int = R.color.design_default_color_error

    override fun secondaryColor(): Int = R.color.cardview_shadow_start_color

    override suspend fun getNetwork(url: String, responseClass: Any): Any {
        return Any()
    }

    override suspend fun postNetwork(url: String, params: Any, responseClass: Any): Any {
        val jsonString = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"idProduct\": \"1234\",\n" +
                "            \"value\": \"R\$ 500,00\",\n" +
                "            \"description\": \"Teste cama\",\n" +
                "            \"stock\": \"100\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"idProduct\": \"1234\",\n" +
                "            \"value\": \"R\$ 500,00\",\n" +
                "            \"description\": \"Teste cama\",\n" +
                "            \"stock\": \"100\"\n" +
                "        }\n" +
                "    ]\n" +
                "}"
        val gson = Gson()
        delay(2000)
        return gson.fromJson(jsonString, ShoppingResponse::class.java)
    }

    override fun navigate(context: Context, route: String, params: HashMap<String, Serializable>) {
        Toast.makeText(context, "Direcionamento: $route", Toast.LENGTH_SHORT).show()
    }

    override fun deleteCache(key: String) {
        // deleta cache
    }

    override fun getCache(key: String, cacheClass: Any): Any {
            return mutableListOf(ShoppingModel("1234", "R$ 500,00", "Teste cama", 100))
    }

    override fun putCache(key: String, data: Any) {
        /// puts cache
    }
}
