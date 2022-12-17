package model

class ShoppingCart {

    private val items: MutableList<ProductQuantity> = mutableListOf()
    private val productWithQuantities: MutableMap<Product, Double> = mutableMapOf()

    internal fun getItems(): List<ProductQuantity> {
        return items.toList()
    }

    internal fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }

    internal fun productWithQuantities(): Map<Product, Double> {
        return productWithQuantities.toMap()
    }

    fun addItemQuantity(product: Product, quantity: Double) {
        items.add(ProductQuantity(product, quantity))
        productWithQuantities[product] = (productWithQuantities[product] ?: 0.0) + quantity
    }
}