package model.offer

import model.Product
import model.discount.Discount
import model.discount.GetItemsForAmountDiscount

class FiveForAmountOffer(
    product: Product,
    private val price: Double
): Offer(product) {

    override fun getDiscount(quantity: Double, unitPrice: Double): Discount? =
        if (quantity.toInt() >= 5) {
            GetItemsForAmountDiscount(
                unitPrice = unitPrice,
                items = 5,
                amount = price
            ).get(quantity, product)

        } else null
}