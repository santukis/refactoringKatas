package model.discount

import model.Offer
import model.Product
import model.SupermarketCatalog

abstract class GetDiscount(
    protected val catalog: SupermarketCatalog,
    protected val offer: Offer
) {

    fun get(quantity: Double, product: Product): Discount =
        Discount(
            product = product,
            description = getDiscountDescription(),
            discountAmount = getDiscountAmount(quantity, product)
        )

    abstract fun getDiscountDescription(): String

    abstract fun getDiscountAmount(quantity: Double, product: Product): Double
}