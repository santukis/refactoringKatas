package model.catalog

import model.offer.Offer

interface SupermarketCatalog {

    fun addOffer(offer: Offer<*>)

    fun getOffers(): List<Offer<*>>
}