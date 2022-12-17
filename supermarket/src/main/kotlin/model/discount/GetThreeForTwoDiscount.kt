package model.discount

import model.Product
import model.SupermarketCatalog

class GetThreeForTwoDiscount(
    catalog: SupermarketCatalog
) : GetDiscount(catalog) {

    override fun getDiscountDescription(): String = "3 for 2"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)

        return GetItemsForAmountDiscount(
            catalog = catalog,
            items = 3,
            amount = 2 * unitPrice
        ).getDiscountAmount(quantity, product)
    }
}