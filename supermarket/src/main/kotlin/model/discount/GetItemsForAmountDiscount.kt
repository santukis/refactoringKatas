package model.discount

import model.Product
import model.SupermarketCatalog

class GetItemsForAmountDiscount(
    catalog: SupermarketCatalog,
    private val items: Int,
    private val amount: Double) : GetDiscount(catalog) {

    override fun getDiscountDescription(): String = "$items for $amount"

    override fun getDiscountAmount(quantity: Double, product: Product): Double {
        val unitPrice = catalog.getUnitPrice(product)
        val itemsWithDiscount = quantity.toInt() / items
        val fiveForAmountDiscount =  amount * itemsWithDiscount + (quantity.toInt() % items * unitPrice)
        return (quantity * unitPrice) - fiveForAmountDiscount
    }
}