package model

class ShoppingCart {

    private val productWithQuantities: MutableMap<Product, Double> = mutableMapOf()

    internal fun productWithQuantities(): Map<Product, Double> {
        return productWithQuantities.toMap()
    }

    fun addItemQuantity(product: Product, quantity: Double) {
        productWithQuantities[product] = (productWithQuantities[product] ?: 0.0) + quantity
    }
}