package model.offer

import model.product.Product
import model.discount.Discount
import model.product.ProductQuantity

abstract class Offer<Item: Product>(val product: Item) {

    fun getDiscount(productQuantities: List<ProductQuantity>): Discount? =
        if (shouldDiscountBeApplied(productQuantities)) {
            Discount(
                product = product,
                description = getDiscountDescription(),
                discountAmount = getDiscountAmount(productQuantities)
            )
        } else null

    abstract fun shouldDiscountBeApplied(productQuantities: List<ProductQuantity>): Boolean

    abstract fun getDiscountDescription(): String

    abstract fun getDiscountAmount(productQuantities: List<ProductQuantity>): Double

    protected fun findProductQuantity(productQuantities: List<ProductQuantity>): ProductQuantity? =
        productQuantities.find { it.product == product }
}