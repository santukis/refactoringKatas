package model.discount

import model.Product

class GetThreeForTwoDiscount(
    unitPrice: Double
) : GetDiscount(unitPrice) {

    override fun getDiscountDescription(): String = "3 for 2"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        return GetItemsForAmountDiscount(
            unitPrice = unitPrice,
            items = 3,
            amount = 2 * unitPrice
        ).getDiscountAmount(quantity, product)
    }
}