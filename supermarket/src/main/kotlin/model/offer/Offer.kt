package model.offer

import model.Product
import model.SupermarketCatalog
import model.discount.*

abstract class Offer(
    protected val catalog: SupermarketCatalog,
    protected val product: Product,
    protected val argument: Double
) {
    abstract fun getDiscount(quantity: Double): Discount?
}