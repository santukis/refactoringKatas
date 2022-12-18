import CatalogDataProvider.addSpecialOffers
import CatalogDataProvider.createCatalog
import model.*
import model.catalog.SupermarketCatalog
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class ReceiptPrinterTest {

    private lateinit var printer: ReceiptPrinter
    private lateinit var fakeCatalog: SupermarketCatalog
    private lateinit var teller: Teller

    @BeforeEach
    fun setup() {
        printer = ReceiptPrinter()
        fakeCatalog = createCatalog()
        teller = Teller(catalog = fakeCatalog)
    }

    @ParameterizedTest
    @MethodSource("CatalogDataProvider#getShoppingCartWithoutSpecialOffers")
    fun printReceiptWithoutSpecialOffers(
        shoppingCart: ShoppingCart,
        expectedReceiptFile: String
    ) {
        checkPrintReceipt(shoppingCart, expectedReceiptFile)
    }

    @ParameterizedTest
    @MethodSource("CatalogDataProvider#getShoppingCartWithSpecialOffers")
    fun printReceiptWithSpecialOffers(
        shoppingCart: ShoppingCart,
        expectedReceiptFile: String
    ) {
        fakeCatalog.addSpecialOffers()
        checkPrintReceipt(shoppingCart, expectedReceiptFile)
    }

    private fun checkPrintReceipt(shoppingCart: ShoppingCart, expectedReceiptFile: String) {
        val receipt = teller.createReceiptFrom(shoppingCart)
        val expectedReceipt = loadExpectedReceipt(expectedReceiptFile)
        assertEquals(expectedReceipt, printer.printReceipt(receipt))
    }

    private fun loadExpectedReceipt(name: String): String =
        ReceiptPrinterTest::class.java.getResource(name)?.readText().orEmpty()
}