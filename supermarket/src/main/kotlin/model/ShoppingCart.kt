package model

import model.product.Product
import model.product.ProductQuantity
import model.product.SingleProduct

class ShoppingCart {

    private val productQuantities: MutableMap<Product, ProductQuantity> = mutableMapOf()

    internal fun productQuantities(): List<ProductQuantity> = productQuantities.values.toList()

    fun addItemQuantity(product: SingleProduct, quantity: Double) {
        productQuantities[product] = updateItemQuantity(product, quantity)
    }

    private fun updateItemQuantity(product: SingleProduct, quantity: Double): ProductQuantity =
        productQuantities[product]?.updateQuantity(quantity) ?: ProductQuantity(product, quantity)
}