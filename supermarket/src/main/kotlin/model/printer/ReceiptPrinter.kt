package model.printer

import model.product.ProductQuantity
import model.product.ProductUnit
import model.receipt.Receipt
import java.util.*

abstract class ReceiptPrinter {

    fun printReceipt(receipt: Receipt): String {
        val result = StringBuilder()
        result.printProducts(receipt)
        result.printDiscounts(receipt)
        result.printTotal(receipt)
        return result.toString()
    }

    protected abstract fun StringBuilder.printProducts(receipt: Receipt)

    protected abstract fun StringBuilder.printDiscounts(receipt: Receipt)

    protected abstract fun StringBuilder.printTotal(receipt: Receipt)

    protected fun getFormattedPrice(price: Double): String {
        return String.format(Locale.UK, "%.2f", price)
    }

    protected fun presentQuantity(item: ProductQuantity): String {
        return if (ProductUnit.Each == item.product.unit)
            String.format("%x", item.quantity.toInt())
        else
            String.format(Locale.UK, "%.3f", item.quantity)
    }

    protected fun getWhitespace(whitespaceSize: Int): String {
        val whitespace = StringBuilder()
        for (i in 0 until whitespaceSize) {
            whitespace.append(" ")
        }
        return whitespace.toString()
    }
}