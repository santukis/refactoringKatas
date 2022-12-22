package model.offer

import model.product.BundleProduct
import model.product.ProductQuantity

class PercentDiscountBundleOffer(
    product: BundleProduct,
    private val percentage: Double
): Offer<BundleProduct>(product) {

    override fun shouldDiscountBeApplied(productQuantities: List<ProductQuantity>): Boolean = productQuantities.hasBundleProducts()

    override fun getDiscountDescription(): String = "$percentage% off"

    override fun getDiscountAmount(productQuantities: List<ProductQuantity>): Double  {
        var discountAmount = 0.0

        var updatedProductQuantities = productQuantities.toMutableList()

        while (updatedProductQuantities.hasBundleProducts()) {
            updatedProductQuantities = updatedProductQuantities.updateProductQuantities()
            discountAmount += product.unitPrice * (percentage / 100.0)
        }

        return discountAmount
    }

    private fun List<ProductQuantity>.hasBundleProducts(): Boolean  =
        map { it.product }.containsAll(product.bundle)

    private fun List<ProductQuantity>.updateProductQuantities(): MutableList<ProductQuantity> {
        val updatedProductQuantities = mutableListOf<ProductQuantity>()

        forEach { productQuantity ->
            val updatedProductQuantity = product.bundle.find { bundleProduct ->
                bundleProduct == productQuantity.product
            }?.let { productQuantity.updateQuantity(-1.0) }?: productQuantity

            if (updatedProductQuantity.quantity > 0) {
                updatedProductQuantities.add(updatedProductQuantity)
            }
        }

        return updatedProductQuantities
    }
}