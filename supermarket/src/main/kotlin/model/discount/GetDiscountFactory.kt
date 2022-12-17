package model.discount

import model.Offer
import model.SpecialOfferType
import model.SupermarketCatalog

object GetDiscountFactory {

    fun create(
        catalog: SupermarketCatalog,
        offer: Offer,
        quantity: Double
    ): GetDiscount {
        val quantityAsInt = quantity.toInt()

        return when {
            offer.offerType === SpecialOfferType.ThreeForTwo && quantityAsInt > 2 ->
                GetThreeForTwoDiscount(catalog, offer)

            offer.offerType === SpecialOfferType.TwoForAmount && quantityAsInt >= 2 ->
                GetTwoForAmountDiscount(catalog, offer)

            offer.offerType === SpecialOfferType.TenPercentDiscount ->
                GetTenPercentDiscount(catalog, offer)

            offer.offerType === SpecialOfferType.FiveForAmount && quantityAsInt >= 5 ->
                GetFiveForAmountDiscount(catalog, offer)

            else -> NoDiscount(catalog, offer)
        }
    }
}