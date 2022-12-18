package model.offer

import model.Product
import model.SupermarketCatalog
import model.discount.Discount
import model.discount.GetPercentDiscount

class PercentDiscountOffer(
    catalog: SupermarketCatalog,
    product: Product,
    private val argument: Double
): Offer(catalog, product) {

    override fun getDiscount(quantity: Double): Discount =
        GetPercentDiscount(catalog, argument).get(quantity, product)
}