package model.discount

import model.Offer
import model.Product
import model.SupermarketCatalog

class NoDiscount(
    catalog: SupermarketCatalog,
    offer: Offer
) : GetDiscount(catalog, offer) {

    override fun getDiscountDescription(): String = ""

    override fun getDiscountAmount(quantity: Double, product: Product): Double = 0.0
}