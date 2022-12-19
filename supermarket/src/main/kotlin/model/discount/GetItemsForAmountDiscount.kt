package model.discount

import model.Product

class GetItemsForAmountDiscount(
    unitPrice: Double,
    private val items: Int,
    private val amount: Double) : GetDiscount(unitPrice) {

    override fun getDiscountDescription(): String = "$items for $amount"

    override fun getDiscountAmount(quantity: Double): Double {
        val itemsWithDiscount = quantity.toInt() / items
        val itemsForAmountDiscount =  amount * itemsWithDiscount + (quantity.toInt() % items * unitPrice)
        return (quantity * unitPrice) - itemsForAmountDiscount
    }
}