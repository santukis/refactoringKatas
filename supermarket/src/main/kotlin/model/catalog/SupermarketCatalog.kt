package model.catalog

import model.Product
import model.offer.Offer

interface SupermarketCatalog {
    fun addProduct(product: Product, price: Double)

    fun getUnitPrice(product: Product): Double

    fun addOffer(offer: Offer)

    fun getOfferFor(product: Product): Offer?
}