package model.offer

import model.Product
import model.discount.Discount

abstract class Offer(val product: Product) {
    abstract fun getDiscount(quantity: Double, unitPrice: Double): Discount?
}