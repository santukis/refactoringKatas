package model.discount

import model.Offer
import model.Product
import model.SupermarketCatalog

class GetFiveForAmountDiscount(
    catalog: SupermarketCatalog,
    offer: Offer
) : GetDiscount(catalog, offer) {

    override fun getDiscountDescription(): String = "5 for ${offer.argument}"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)
        val itemsWithDiscount = quantity.toInt() / 5
        val fiveForAmountDiscount =  offer.argument * itemsWithDiscount + quantity.toInt() % 5 * unitPrice
        return (quantity * unitPrice) - fiveForAmountDiscount
    }
}