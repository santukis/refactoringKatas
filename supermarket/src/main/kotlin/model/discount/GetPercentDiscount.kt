package model.discount

import model.Product
import model.SupermarketCatalog

class GetPercentDiscount(
    catalog: SupermarketCatalog,
    private val percentage: Double
) : GetDiscount(catalog) {

    override fun getDiscountDescription(): String = "$percentage% off"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)
        val tenPercentDiscount = percentage / 100.0
        return  (quantity * unitPrice) * tenPercentDiscount
    }
}