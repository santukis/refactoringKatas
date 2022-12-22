package model.offer

import model.product.ProductQuantity
import model.product.SingleProduct
import java.lang.Exception

class PercentDiscountOffer(
    product: SingleProduct,
    private val percentage: Double
): Offer<SingleProduct>(product) {

    override fun shouldDiscountBeApplied(productQuantities: List<ProductQuantity>): Boolean =
        findProductQuantity(productQuantities) != null

    override fun getDiscountDescription(): String = "$percentage% off"

    override fun getDiscountAmount(productQuantities: List<ProductQuantity>): Double {
        val productQuantity = findProductQuantity(productQuantities) ?: throw Exception()
        return productQuantity.getTotalPrice() * (percentage / 100.0)
    }
}