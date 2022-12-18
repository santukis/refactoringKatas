package model.offer

import model.Product
import model.discount.Discount
import model.discount.GetThreeForTwoDiscount

class ThreeForTwoOffer(
    product: Product,
): Offer(product) {

    override fun getDiscount(quantity: Double, unitPrice: Double): Discount? =
        if (quantity.toInt() > 2) {
            GetThreeForTwoDiscount(unitPrice).get(quantity, product)

        } else null
}