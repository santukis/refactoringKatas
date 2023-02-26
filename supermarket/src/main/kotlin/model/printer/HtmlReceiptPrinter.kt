package model.printer

import model.receipt.Receipt

class HtmlReceiptPrinter @JvmOverloads constructor(private val columns: Int = 40): ReceiptPrinter() {

    companion object {
        private const val INDENTATION_SPACES = 4
    }

    override fun StringBuilder.printProducts(receipt: Receipt) {
        startHtml()

        receipt.getReceiptItem().products.forEach { item ->
            val price = getFormattedPrice(item.getTotalPrice())
            val quantity = presentQuantity(item)
            val name = item.product.name
            val unitPrice = getFormattedPrice(item.product.unitPrice)
            val whitespaceSize = columns - name.length - price.length

            append("\n${getWhitespace(INDENTATION_SPACES * 2)}")
            append("<p>${name + getWhitespace(whitespaceSize) + price }</p>")

            if (item.quantity != 1.0) {
                append("\n${getWhitespace(INDENTATION_SPACES * 3)}")
                append("<p>$unitPrice * $quantity</p>")
            }
        }
    }

    override fun StringBuilder.printDiscounts(receipt: Receipt) {
        receipt.getReceiptItem().discounts.forEach { discount ->
            val productPresentation = discount.product.name
            val pricePresentation = getFormattedPrice(discount.discountAmount)
            val description = discount.description
            append("\n${getWhitespace(INDENTATION_SPACES * 2)}")
            append("<p>")
            append(discount.getDiscountHeader())
            append(getWhitespace(columns - 3 - productPresentation.length - description.length - pricePresentation.length))
            append("-")
            append(pricePresentation)
            append("</p>")
        }
    }

    override fun StringBuilder.printTotal(receipt: Receipt) {
        val pricePresentation = getFormattedPrice(receipt.getTotalPrice())
        val total = "Total: "
        val whitespace = getWhitespace(columns - total.length - pricePresentation.length)
        val row = total + whitespace + pricePresentation

        append("\n")
        append("""
                <p>$row</p>
            </body>
        </html>
        """.trimIndent())
    }

    private fun StringBuilder.startHtml() {
        append("""
            <!DOCTYPE html>
            <html>
                <head>
                    <meta charset="utf-8" />
                    <title>Receipt</title>
                <head>
                <body>
        """.trimIndent())
    }
}