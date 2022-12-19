package model.discount

import model.Product

abstract class GetDiscount(protected val unitPrice: Double) {

    fun get(quantity: Double, product: Product): Discount =
        Discount(
            product = product,
            description = getDiscountDescription(),
            discountAmount = getDiscountAmount(quantity)
        )

    abstract fun getDiscountDescription(): String

    abstract fun getDiscountAmount(quantity: Double): Double
}