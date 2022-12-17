package model.discount

import model.Offer
import model.Product
import model.SupermarketCatalog

class GetTenPercentDiscount(
    catalog: SupermarketCatalog,
    offer: Offer
) : GetDiscount(catalog, offer) {

    override fun getDiscountDescription(): String = "${offer.argument}% off"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)
        val tenPercentDiscount = offer.argument / 100.0
        return  (quantity * unitPrice) * tenPercentDiscount
    }
}