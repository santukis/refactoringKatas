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
        shoppingCart.productQuantities().forEach { productQuantity ->
            val product = productQuantity.key
            val quantity = productQuantity.value

            offers[product]?.let { offer ->
                val unitPrice = catalog.getUnitPrice(product)
                val quantityAsInt = quantity.toInt()
                var discount: Discount? = null
                var requiredItemsForDiscount = 1

                if (offer.offerType === SpecialOfferType.ThreeForTwo) {
                    requiredItemsForDiscount = 3

                } else if (offer.offerType === SpecialOfferType.TwoForAmount) {
                    requiredItemsForDiscount = 2

                } else if (offer.offerType === SpecialOfferType.FiveForAmount) {
                    requiredItemsForDiscount = 5
                }

                val itemsWithDiscount = quantityAsInt / requiredItemsForDiscount

                if (offer.offerType === SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    val discountAmount =
                        quantity * unitPrice - (itemsWithDiscount.toDouble() * 2.0 * unitPrice + quantityAsInt % 3 * unitPrice)
                    discount = Discount(product, "3 for 2", discountAmount)

                } else if (offer.offerType === SpecialOfferType.TwoForAmount && quantityAsInt >= 2) {
                    val total = offer.argument * (quantityAsInt / requiredItemsForDiscount) + quantityAsInt % 2 * unitPrice
                    val discountN = unitPrice * quantity - total
                    discount = Discount(product, "2 for " + offer.argument, discountN)

                } else if (offer.offerType === SpecialOfferType.TenPercentDiscount) {
                    discount =
                        Discount(product, offer.argument.toString() + "% off", quantity * unitPrice * offer.argument / 100.0)

                } else if (offer.offerType === SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    val discountTotal =
                        unitPrice * quantity - (offer.argument * itemsWithDiscount + quantityAsInt % 5 * unitPrice)
                    discount = Discount(product, requiredItemsForDiscount.toString() + " for " + offer.argument, discountTotal)
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