package model.discount

import model.product.Product

class Discount(
    val product: Product,
    val description: String,
    val discountAmount: Double) {

    fun getDiscountHeader(): String = "${description}(${product.name})"
}
