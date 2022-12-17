package model.discount

import model.Offer
import model.SpecialOfferType
import model.SupermarketCatalog

class GetDiscountFactory(private val catalog: SupermarketCatalog) {

    fun create(offer: Offer, quantity: Int): GetDiscount {
        return when {
            offer.offerType === SpecialOfferType.ThreeForTwo && quantity > 2 ->
                GetThreeForTwoDiscount(catalog, offer)

            offer.offerType === SpecialOfferType.TwoForAmount && quantity >= 2 ->
                GetTwoForAmountDiscount(catalog, offer)

            offer.offerType === SpecialOfferType.TenPercentDiscount ->
                GetTenPercentDiscount(catalog, offer)

            offer.offerType === SpecialOfferType.FiveForAmount && quantity >= 5 ->
                GetFiveForAmountDiscount(catalog, offer)

            else -> NoDiscount(catalog, offer)
        }
    }
}