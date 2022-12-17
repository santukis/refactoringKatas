package model.discount

import model.Offer
import model.SpecialOfferType
import model.SupermarketCatalog

class GetDiscountFactory(private val catalog: SupermarketCatalog) {

    fun create(offer: Offer, quantity: Int): GetDiscount {
        return when {
            offer.offerType === SpecialOfferType.ThreeForTwo && quantity > 2 ->
                GetThreeForTwoDiscount(catalog)

            offer.offerType === SpecialOfferType.TwoForAmount && quantity >= 2 ->
                GetItemsForAmountDiscount(
                    catalog = catalog,
                    items = 2,
                    amount = offer.argument
                )

            offer.offerType === SpecialOfferType.TenPercentDiscount ->
                GetPercentDiscount(catalog, offer.argument)

            offer.offerType === SpecialOfferType.FiveForAmount && quantity >= 5 ->
                GetItemsForAmountDiscount(
                    catalog = catalog,
                    items = 5,
                    amount = offer.argument
                )

            else -> NoDiscount(catalog)
        }
    }
}