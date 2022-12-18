package model

import model.catalog.SupermarketCatalog

class Teller(private val catalog: SupermarketCatalog) {

    fun createReceiptFrom(shoppingCart: ShoppingCart): Receipt =
        Receipt(catalog).apply {
            addProducts(shoppingCart)
            addDiscounts(shoppingCart)
        }
}