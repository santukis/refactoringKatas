package model.offer

import model.Product
import model.discount.Discount
import model.discount.GetPercentDiscount

class PercentDiscountOffer(
    product: Product,
    private val argument: Double
): Offer(product) {

    override fun getDiscount(quantity: Double, unitPrice: Double): Discount =
        GetPercentDiscount(unitPrice, argument).get(quantity, product)
}