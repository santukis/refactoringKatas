package model.offer

import model.Product
import model.discount.Discount
import model.discount.GetItemsForAmountDiscount

class TwoForAmountOffer(
    product: Product,
    private val price: Double
): Offer(product) {

    override fun getDiscount(quantity: Double, unitPrice: Double): Discount? =
        if (quantity.toInt() >= 2) {
            GetItemsForAmountDiscount(
                unitPrice = unitPrice,
                items = 2,
                amount = price
            ).get(quantity, product)

        } else null
}