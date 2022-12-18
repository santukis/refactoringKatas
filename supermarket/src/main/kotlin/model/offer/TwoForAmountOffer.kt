package model.offer

import model.Product
import model.SupermarketCatalog
import model.discount.Discount
import model.discount.GetItemsForAmountDiscount

class TwoForAmountOffer(
    catalog: SupermarketCatalog,
    product: Product,
    private val argument: Double
): Offer(catalog, product) {

    override fun getDiscount(quantity: Double): Discount? =
        if (quantity.toInt() >= 2) {
            GetItemsForAmountDiscount(
                catalog = catalog,
                items = 2,
                amount = argument
            ).get(quantity, product)

        } else null
}