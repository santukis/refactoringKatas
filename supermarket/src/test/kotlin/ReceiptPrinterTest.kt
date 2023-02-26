import model.ShoppingCart
import model.Teller
import model.catalog.SupermarketCatalog
import model.printer.ReceiptPrinter
import org.junit.jupiter.api.Assertions.assertEquals

internal open class ReceiptPrinterTest {

    protected lateinit var printer: ReceiptPrinter
    protected lateinit var fakeCatalog: SupermarketCatalog
    protected lateinit var teller: Teller

    protected fun checkPrintReceipt(shoppingCart: ShoppingCart, expectedReceiptFile: String) {
        val receipt = teller.createReceiptFrom(shoppingCart)
        val expectedReceipt = loadExpectedReceipt(expectedReceiptFile)
        assertEquals(expectedReceipt, printer.printReceipt(receipt))
    }

    private fun loadExpectedReceipt(name: String): String =
        ReceiptPrinterTest::class.java.getResource(name)?.readText().orEmpty()
}