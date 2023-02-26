package model.printer

import model.receipt.Receipt

class PlainTextReceiptPrinter @JvmOverloads constructor(private val columns: Int = 40): ReceiptPrinter() {

    override fun StringBuilder.printProducts(receipt: Receipt) {
        receipt.getReceiptItem().products.forEach { item ->
            val price = getFormattedPrice(item.getTotalPrice())
            val quantity = presentQuantity(item)
            val name = item.product.name
            val unitPrice = getFormattedPrice(item.product.unitPrice)

            val whitespaceSize = columns - name.length - price.length
            var line = name + getWhitespace(whitespaceSize) + price + "\n"

            if (item.quantity != 1.0) {
                line += "  $unitPrice * $quantity\n"
            }

            append(line)
        }
    }

    override fun StringBuilder.printDiscounts(receipt: Receipt) {
        receipt.getReceiptItem().discounts.forEach { discount ->
            val productPresentation = discount.product.name
            val pricePresentation = getFormattedPrice(discount.discountAmount)
            val description = discount.description
            append(discount.getDiscountHeader())
            append(getWhitespace(columns - 3 - productPresentation.length - description.length - pricePresentation.length))
            append("-")
            append(pricePresentation)
            append("\n")
        }

        append("\n")
    }

    override fun StringBuilder.printTotal(receipt: Receipt) {
        val pricePresentation = getFormattedPrice(receipt.getTotalPrice())
        val total = "Total: "
        val whitespace = getWhitespace(columns - total.length - pricePresentation.length)
        append(total).append(whitespace).append(pricePresentation)
    }
}