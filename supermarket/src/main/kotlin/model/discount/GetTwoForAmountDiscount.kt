package model.discount

import model.Offer
import model.Product
import model.SupermarketCatalog

class GetTwoForAmountDiscount(
    catalog: SupermarketCatalog,
    offer: Offer
) : GetDiscount(catalog, offer) {

    override fun getDiscountDescription(): String = "2 for ${offer.argument}"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)
        val twoForAmountDiscount = offer.argument * (quantity.toInt() / 2) + quantity.toInt() % 2 * unitPrice
        return (quantity * unitPrice) - twoForAmountDiscount
    }
}