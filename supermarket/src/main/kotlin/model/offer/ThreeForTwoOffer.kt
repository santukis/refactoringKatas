package model.offer

import model.product.ProductQuantity
import model.product.SingleProduct

class ThreeForTwoOffer(
    product: SingleProduct,
): ItemsForAmountOffer(product, 3, product.unitPrice * 2) {

    override fun shouldDiscountBeApplied(productQuantities: List<ProductQuantity>): Boolean =
        (findProductQuantity(productQuantities)?.quantity?.toInt() ?: 0) > 2

    override fun getDiscountDescription(): String = "3 for 2"
}