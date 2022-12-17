package model.discount

import model.Offer
import model.Product
import model.SupermarketCatalog

class GetThreeForTwoDiscount(
    catalog: SupermarketCatalog,
    offer: Offer
) : GetDiscount(catalog, offer) {

    override fun getDiscountDescription(): String = "3 for 2"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)
        val itemsWithDiscount = quantity.toInt() / 3
        val threeForTwoDiscount = itemsWithDiscount.toDouble() * 2.0 * unitPrice + quantity.toInt() % 3 * unitPrice
        return (quantity * unitPrice) - threeForTwoDiscount
    }
}