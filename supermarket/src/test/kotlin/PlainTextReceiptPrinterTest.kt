import CatalogDataProvider.addSpecialOffers
import model.*
import model.catalog.DefaultCatalog
import model.printer.PlainTextReceiptPrinter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class PlainTextReceiptPrinterTest: ReceiptPrinterTest() {

    @BeforeEach
    fun setup() {
        printer = PlainTextReceiptPrinter()
        fakeCatalog = DefaultCatalog()
        teller = Teller(catalog = fakeCatalog)
    }

    @ParameterizedTest
    @MethodSource("CatalogDataProvider#getShoppingCartWithoutSpecialOffersOnPlainText")
    fun printReceiptWithoutSpecialOffers(
        shoppingCart: ShoppingCart,
        expectedReceiptFile: String
    ) {
        checkPrintReceipt(shoppingCart, expectedReceiptFile)
    }

    @ParameterizedTest
    @MethodSource("CatalogDataProvider#getShoppingCartWithSpecialOffersOnPlainText")
    fun printReceiptWithSpecialOffers(
        shoppingCart: ShoppingCart,
        expectedReceiptFile: String
    ) {
        fakeCatalog.addSpecialOffers()
        checkPrintReceipt(shoppingCart, expectedReceiptFile)
    }
}