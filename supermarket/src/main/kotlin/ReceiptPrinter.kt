import model.product.ProductUnit
import model.Receipt
import model.product.ProductQuantity
import java.util.*

class ReceiptPrinter @JvmOverloads constructor(private val columns: Int = 40) {

    fun printReceipt(receipt: Receipt): String {
        val result = StringBuilder()
        for (item in receipt.getRecipeItems()) {
            val price = String.format(Locale.UK, "%.2f", item.getTotalPrice())
            val quantity = presentQuantity(item)
            val name = item.product.name
            val unitPrice = String.format(Locale.UK, "%.2f", item.product.unitPrice)

            val whitespaceSize = this.columns - name.length - price.length
            var line = name + getWhitespace(whitespaceSize) + price + "\n"

            if (item.quantity != 1.0) {
                line += "  $unitPrice * $quantity\n"
            }
            result.append(line)
        }
        for (discount in receipt.getDiscounts()) {
            val productPresentation = discount.product.name
            val pricePresentation = String.format(Locale.UK, "%.2f", discount.discountAmount)
            val description = discount.description
            result.append(description)
            result.append("(")
            result.append(productPresentation)
            result.append(")")
            result.append(getWhitespace(this.columns - 3 - productPresentation.length - description.length - pricePresentation.length))
            result.append("-")
            result.append(pricePresentation)
            result.append("\n")
        }
        result.append("\n")
        val pricePresentation = String.format(Locale.UK, "%.2f", receipt.getTotalPrice())
        val total = "Total: "
        val whitespace = getWhitespace(this.columns - total.length - pricePresentation.length)
        result.append(total).append(whitespace).append(pricePresentation)
        return result.toString()
    }

    private fun presentQuantity(item: ProductQuantity): String {
        return if (ProductUnit.Each == item.product.unit)
            String.format("%x", item.quantity.toInt())
        else
            String.format(Locale.UK, "%.3f", item.quantity)
    }

    private fun getWhitespace(whitespaceSize: Int): String {
        val whitespace = StringBuilder()
        for (i in 0 until whitespaceSize) {
            whitespace.append(" ")
        }
        return whitespace.toString()
    }
}