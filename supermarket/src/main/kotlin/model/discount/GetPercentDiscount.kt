package model.discount

import model.Product

class GetPercentDiscount(
    unitPrice: Double,
    private val percentage: Double
) : GetDiscount(unitPrice) {

    override fun getDiscountDescription(): String = "$percentage% off"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val tenPercentDiscount = percentage / 100.0
        return  (quantity * unitPrice) * tenPercentDiscount
    }
}