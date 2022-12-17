package model

import model.discount.*

class Receipt(private val catalog: SupermarketCatalog) {

    val items = mutableListOf<ReceiptItem>()
    val discounts = mutableListOf<Discount>()

    fun getTotalPrice(): Double {
        var result = 0.0
        result += getTotalItemPrice()
        result -= getTotalDiscounts()
        return result
    }

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
            offers[product]?.let { offer ->
                val getDiscount: GetDiscount = GetDiscountFactory(catalog).create(offer, quantity.toInt())
                discounts.add(getDiscount.get(quantity, product))
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