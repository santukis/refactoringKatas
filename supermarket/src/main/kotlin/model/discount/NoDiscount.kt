package model.discount

import model.Product
import model.SupermarketCatalog

class NoDiscount(
    catalog: SupermarketCatalog
) : GetDiscount(catalog) {

    override fun getDiscountDescription(): String = ""

    override fun getDiscountAmount(quantity: Double, product: Product): Double = 0.0
}