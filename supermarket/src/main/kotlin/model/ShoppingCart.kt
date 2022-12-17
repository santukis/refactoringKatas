package model

class ShoppingCart {

    private val items: MutableList<ProductQuantity> = mutableListOf()
    private val productQuantities: MutableMap<Product, Double> = mutableMapOf()

    internal fun getItems(): List<ProductQuantity> {
        return items.toList()
    }

    internal fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }

    internal fun productQuantities(): Map<Product, Double> {
        return productQuantities.toMap()
    }

    fun addItemQuantity(product: Product, quantity: Double) {
        items.add(ProductQuantity(product, quantity))
        productQuantities[product] = (productQuantities[product] ?: 0.0) + quantity
    }
}