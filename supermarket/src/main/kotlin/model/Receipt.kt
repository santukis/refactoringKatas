package model

import model.discount.*

class Receipt(private val catalog: SupermarketCatalog) {

    private val items = ArrayList<ReceiptItem>()
    private val discounts = ArrayList<Discount>()

    fun getTotalPrice(): Double {
        var total = 0.0
        total += getTotalItemPrice()
        total -= getTotalDiscounts()
        return total
    }

    fun getItems(): List<ReceiptItem> {
        return ArrayList(this.items)
    }

    fun getDiscounts(): List<Discount> {
        return discounts
    }

    fun addProducts(theCart: ShoppingCart) {
        val productQuantities = theCart.getItems()

        for (productQuantity in productQuantities) {
            items.add(createReceiptItem(productQuantity))
        }
    }

    fun handleOffers(
        shoppingCart: ShoppingCart,
        offers: Map<Product, Offer>
    ) {
        shoppingCart.productWithQuantities().forEach { (product, quantity) ->
            offers[product]?.let { offer ->
                val getDiscount: GetDiscount = GetDiscountFactory.create(catalog, offer, quantity)
                discounts.add(getDiscount.get(quantity, product))
            }
        }
    }

    private fun createReceiptItem(productQuantity: ProductQuantity): ReceiptItem {
        val product = productQuantity.product
        val quantity = productQuantity.quantity
        val price = catalog.getUnitPrice(product)
        val totalPrice = quantity * price

        return ReceiptItem(
            product = product,
            quantity = quantity,
            price = price,
            totalPrice = totalPrice
        )
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
}