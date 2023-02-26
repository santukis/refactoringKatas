import CatalogDataProvider.addSpecialOffers
import model.*
import model.catalog.DefaultCatalog
import model.printer.HtmlReceiptPrinter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

internal class HtmlReceiptPrinterTest: ReceiptPrinterTest() {

    @BeforeEach
    fun setup() {
        printer = HtmlReceiptPrinter()
        fakeCatalog = DefaultCatalog()
        teller = Teller(catalog = fakeCatalog)
    }

    @ParameterizedTest
    @MethodSource("CatalogDataProvider#getShoppingCartWithoutSpecialOffersOnHtml")
    fun printReceiptWithoutSpecialOffers(
        shoppingCart: ShoppingCart,
        expectedReceiptFile: String
    ) {
        checkPrintReceipt(shoppingCart, expectedReceiptFile)
    }

    @ParameterizedTest
    @MethodSource("CatalogDataProvider#getShoppingCartWithSpecialOffersOnHtml")
    fun printReceiptWithSpecialOffers(
        shoppingCart: ShoppingCart,
        expectedReceiptFile: String
    ) {
        fakeCatalog.addSpecialOffers()
        checkPrintReceipt(shoppingCart, expectedReceiptFile)
    }
}