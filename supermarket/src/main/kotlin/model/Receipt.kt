package model

import model.discount.Discount
import model.offer.Offer

class Receipt(private val catalog: SupermarketCatalog) {

    private val items = mutableListOf<ReceiptItem>()
    private val discounts = mutableListOf<Discount>()

    fun getTotalPrice(): Double {
        var result = 0.0
        result += getTotalItemPrice()
        result -= getTotalDiscounts()
        return result
    }

    fun getItems(): List<ReceiptItem> = items.toList()

    fun getDiscounts(): List<Discount> = discounts.toList()

    fun addProducts(shoppingCart: ShoppingCart) {
        shoppingCart.productWithQuantities().forEach { (product, quantity) ->
            items.add(createReceiptItem(product, quantity))
        }
    }

    fun addDiscounts(
        shoppingCart: ShoppingCart,
        offers: Map<Product, Offer>
    ) {
        shoppingCart.productWithQuantities().forEach { (product, quantity) ->
            offers[product]?.getDiscount(quantity)?.let { discount ->
                discounts.add(discount)
            }
        }
    }

    private fun getTotalItemPrice(): Double {
        var result = 0.0

        items.forEach { item ->
            result += item.totalPrice
        }

        return result
    }

    private fun getTotalDiscounts(): Double {
        var result = 0.0

        discounts.forEach { discount ->
            result += discount.discountAmount
        }

        return result
    }

    private fun createReceiptItem(
        product: Product,
        quantity: Double
    ): ReceiptItem {
        val price = catalog.getUnitPrice(product)
        val totalPrice = quantity * price

        return ReceiptItem(
            product = product,
            quantity = quantity,
            price = price,
            totalPrice = totalPrice
        )
    }
}