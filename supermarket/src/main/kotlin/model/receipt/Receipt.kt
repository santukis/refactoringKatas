package model.receipt

import model.ShoppingCart
import model.catalog.SupermarketCatalog
import model.discount.Discount
import model.product.ProductQuantity

class Receipt(private val catalog: SupermarketCatalog) {

    private val productQuantities = mutableListOf<ProductQuantity>()
    private val discounts = mutableListOf<Discount>()

    fun getTotalPrice(): Double {
        var result = 0.0
        result += productQuantities.sumOf { it.getTotalPrice() }
        result -= discounts.sumOf { it.discountAmount }
        return result
    }

    fun getReceiptItem(): ReceiptItem = ReceiptItem(
        products = productQuantities,
        discounts = discounts
    )

    fun addProducts(shoppingCart: ShoppingCart) {
        productQuantities.addAll(shoppingCart.productQuantities())
    }

    fun addDiscounts(shoppingCart: ShoppingCart) {
        catalog.getOffers().forEach { offer ->
            offer.getDiscount(shoppingCart.productQuantities())?.let { discount ->
                discounts.add(discount)
            }
        }
    }
}