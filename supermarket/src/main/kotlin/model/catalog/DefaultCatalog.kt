package model.catalog

import model.Product
import model.offer.Offer

class DefaultCatalog: SupermarketCatalog {

    private val products: MutableMap<String, Double> = mutableMapOf()
    private val offers: MutableMap<Product, Offer> = mutableMapOf()

    override fun addProduct(product: Product, price: Double) {
        products[product.name] = price
    }

    override fun getUnitPrice(product: Product): Double = products[product.name] ?: 0.0

    override fun addOffer(offer: Offer) {
        offers[offer.product] = offer
    }

    override fun getOfferFor(product: Product): Offer? = offers[product]
}