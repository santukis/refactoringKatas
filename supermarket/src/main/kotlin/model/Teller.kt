package model

import model.offer.Offer
import model.offer.OfferFactory
import model.offer.SpecialOfferType

class Teller(private val catalog: SupermarketCatalog) {
    private val offers = mutableMapOf<Product, Offer>()

    fun addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double) {
        offers[product] = OfferFactory(catalog).create(offerType, product, argument)
    }

    fun createReceiptFrom(shoppingCart: ShoppingCart): Receipt =
        Receipt(catalog).apply {
            addProducts(shoppingCart)
            addDiscounts(shoppingCart, offers)
        }
}