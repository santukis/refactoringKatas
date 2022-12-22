package model.catalog

import model.offer.Offer

class DefaultCatalog: SupermarketCatalog {

    private val offers: MutableList<Offer<*>> = mutableListOf()

    override fun addOffer(offer: Offer<*>) {
        offers.add(offer)
    }

    override fun getOffers(): List<Offer<*>> = offers.toList()
}