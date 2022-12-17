package model

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
        for (p in shoppingCart.productQuantities().keys) {
            val quantity = shoppingCart.productQuantities()[p]!!
            if (offers.containsKey(p)) {
                val offer = offers[p]!!
                val unitPrice = catalog.getUnitPrice(p)
                val quantityAsInt = quantity.toInt()
                var discount: Discount? = null
                var x = 1
                if (offer.offerType === SpecialOfferType.ThreeForTwo) {
                    x = 3

                } else if (offer.offerType === SpecialOfferType.TwoForAmount) {
                    x = 2
                    if (quantityAsInt >= 2) {
                        val total = offer.argument * (quantityAsInt / x) + quantityAsInt % 2 * unitPrice
                        val discountN = unitPrice * quantity - total
                        discount = Discount(p, "2 for " + offer.argument, discountN)
                    }

                }
                if (offer.offerType === SpecialOfferType.FiveForAmount) {
                    x = 5
                }
                val numberOfXs = quantityAsInt / x
                if (offer.offerType === SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    val discountAmount =
                        quantity * unitPrice - (numberOfXs.toDouble() * 2.0 * unitPrice + quantityAsInt % 3 * unitPrice)
                    discount = Discount(p, "3 for 2", discountAmount)
                }
                if (offer.offerType === SpecialOfferType.TenPercentDiscount) {
                    discount =
                        Discount(p, offer.argument.toString() + "% off", quantity * unitPrice * offer.argument / 100.0)
                }
                if (offer.offerType === SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    val discountTotal =
                        unitPrice * quantity - (offer.argument * numberOfXs + quantityAsInt % 5 * unitPrice)
                    discount = Discount(p, x.toString() + " for " + offer.argument, discountTotal)
                }
                if (discount != null) {
                    discounts.add(discount)
                }
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