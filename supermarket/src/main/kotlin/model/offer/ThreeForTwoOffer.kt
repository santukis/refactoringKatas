package model.offer

import model.Product
import model.SupermarketCatalog
import model.discount.Discount
import model.discount.GetThreeForTwoDiscount

class ThreeForTwoOffer(
    catalog: SupermarketCatalog,
    product: Product,
): Offer(catalog, product, 0.0) {

    override fun getDiscount(quantity: Double): Discount? =
        if (quantity.toInt() > 2) {
            GetThreeForTwoDiscount(catalog).get(quantity, product)

        } else null
}