package model.offer

import model.Product
import model.SupermarketCatalog
import model.discount.Discount
import model.discount.GetItemsForAmountDiscount

class FiveForAmountOffer(
    catalog: SupermarketCatalog,
    product: Product,
    argument: Double
): Offer(catalog, product, argument) {

    override fun getDiscount(quantity: Double): Discount? =
        if (quantity.toInt() >= 5) {
            GetItemsForAmountDiscount(
                catalog = catalog,
                items = 5,
                amount = argument
            ).get(quantity, product)

        } else null
}