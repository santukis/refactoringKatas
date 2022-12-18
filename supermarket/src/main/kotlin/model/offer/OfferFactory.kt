package model.offer

import model.Product
import model.SupermarketCatalog

class OfferFactory(private val catalog: SupermarketCatalog) {

    fun create(
        offerType: SpecialOfferType,
        product: Product,
        argument: Double
    ): Offer =
        when(offerType) {
            SpecialOfferType.ThreeForTwo -> ThreeForTwoOffer(catalog, product)
            SpecialOfferType.TenPercentDiscount -> PercentDiscountOffer(catalog, product, argument)
            SpecialOfferType.TwoForAmount -> TwoForAmountOffer(catalog, product, argument)
            SpecialOfferType.FiveForAmount -> FiveForAmountOffer(catalog, product, argument)
        }

}