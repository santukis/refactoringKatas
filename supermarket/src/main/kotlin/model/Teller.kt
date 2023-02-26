package model

import model.catalog.SupermarketCatalog
import model.receipt.Receipt

class Teller(private val catalog: SupermarketCatalog) {

    fun createReceiptFrom(shoppingCart: ShoppingCart): Receipt =
        Receipt(catalog).apply {
            addProducts(shoppingCart)
            addDiscounts(shoppingCart)
        }
}