import model.Product
import model.SupermarketCatalog

class FakeCatalog: SupermarketCatalog {

    private val products: MutableMap<String, Double> = mutableMapOf()

    override fun addProduct(product: Product, price: Double) {
        products[product.name] = price
    }

    override fun getUnitPrice(product: Product): Double {
        return products[product.name] ?: 0.0
    }
}