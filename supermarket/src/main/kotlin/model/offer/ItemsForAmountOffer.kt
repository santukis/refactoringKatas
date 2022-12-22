package model.offer

import model.product.ProductQuantity
import model.product.SingleProduct
import java.lang.Exception

open class ItemsForAmountOffer(
    product: SingleProduct,
    private val items: Int,
    private val amount: Double,
): Offer<SingleProduct>(product) {

    override fun shouldDiscountBeApplied(productQuantities: List<ProductQuantity>): Boolean =
        (findProductQuantity(productQuantities)?.quantity?.toInt() ?: 0) >= items

    override fun getDiscountDescription(): String =  "$items for $amount"

    override fun getDiscountAmount(productQuantities: List<ProductQuantity>): Double {
        val productQuantity = findProductQuantity(productQuantities) ?: throw Exception()
        val quantity = productQuantity.quantity
        val itemsWithDiscount = quantity.toInt() / items
        val itemsForAmountDiscount =  amount * itemsWithDiscount + (quantity.toInt() % items * product.unitPrice)
        return productQuantity.getTotalPrice() - itemsForAmountDiscount
    }
}