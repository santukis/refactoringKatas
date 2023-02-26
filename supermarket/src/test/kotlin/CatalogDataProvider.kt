import model.*
import model.catalog.SupermarketCatalog
import model.offer.*
import model.product.BundleProduct
import model.product.ProductUnit
import model.product.SingleProduct
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object CatalogDataProvider {

    fun SupermarketCatalog.addSpecialOffers() {
        addOffer(TwoForAmountOffer(product = getToothBrush(), price = 0.99))
        addOffer(PercentDiscountOffer(product = getApples(), percentage = 20.0))
        addOffer(PercentDiscountOffer(product = getRice(), percentage = 10.0))
        addOffer(FiveForAmountOffer(product = getToothPaste(), price = 7.49))
        addOffer(ThreeForTwoOffer(product = getCherries()))
        addOffer(PercentDiscountBundleOffer(product = BundleProduct(listOf(getToothBrush(), getToothPaste())), percentage = 10.0))
        addOffer(PercentDiscountBundleOffer(product = BundleProduct(listOf(getOranges(), getLemons())), percentage = 15.0))
    }

    @JvmStatic
    fun getShoppingCartWithoutSpecialOffersOnPlainText(): Stream<Arguments> = Stream.of(
        Arguments.of(ShoppingCart(), "/shopping_cart_empty.txt"),
        Arguments.of(
            ShoppingCart()
                .addItem(getToothBrush(), quantity = 1.0)
                .addItem(getToothPaste(), quantity = 1.0)
                .addItem(getApples(), quantity = 2.0)
                .addItem(getRice(), quantity = 1.0)
                .addItem(getCherries(), quantity = 1.0),
            "/shopping_cart_without_special_offers.txt"
        ),
    )

    @JvmStatic
    fun getShoppingCartWithoutSpecialOffersOnHtml(): Stream<Arguments> = Stream.of(
        Arguments.of(ShoppingCart(), "/html_shopping_cart_empty.txt"),
        Arguments.of(
            ShoppingCart()
                .addItem(getToothBrush(), quantity = 1.0)
                .addItem(getToothPaste(), quantity = 1.0)
                .addItem(getApples(), quantity = 2.0)
                .addItem(getRice(), quantity = 1.0)
                .addItem(getCherries(), quantity = 1.0),
            "/html_shopping_cart_without_special_offers.txt"
        ),
    )

    @JvmStatic
    fun getShoppingCartWithSpecialOffersOnPlainText(): Stream<Arguments> = Stream.of(
        Arguments.of(
            ShoppingCart().addItem(getToothBrush(), quantity = 2.0),
            "/shopping_cart_with_special_offer_TwoForAmount.txt"
        ),
        Arguments.of(
            ShoppingCart()
                .addItem(getToothBrush(), quantity = 4.0)
                .addItem(getToothBrush(), quantity = 1.0),
            "/shopping_cart_with_special_offer_TwoForAmountMultipleItems.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getApples(), quantity = 5.0),
            "/shopping_cart_with_special_offer_discount_per_kilo.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getRice(), quantity = 2.0),
            "/shopping_cart_with_special_offer_discount_per_each.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getToothPaste(), quantity = 5.0),
            "/shopping_cart_with_special_offer_FiveForAmount.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getCherries(), quantity = 3.0),
            "/shopping_cart_with_special_offer_ThreeForAmount.txt"
        ),
        Arguments.of(
            ShoppingCart()
                .addItem(getToothBrush(), quantity = 1.0)
                .addItem(getToothPaste(), quantity = 1.0)
                .addItem(getLemons(), quantity = 1.0),
            "/shopping_cart_with_special_offer_PercentDiscountBundle.txt"
        ),
        Arguments.of(
            ShoppingCart()
                .addItem(getOranges(), quantity = 1.0)
                .addItem(getLemons(), quantity = 3.0),
            "/shopping_cart_with_special_offer_PercentDiscountBundle_MultipleItems.txt"
        ),
    )

    @JvmStatic
    fun getShoppingCartWithSpecialOffersOnHtml(): Stream<Arguments> = Stream.of(
        Arguments.of(
            ShoppingCart().addItem(getToothBrush(), quantity = 2.0),
            "/html_shopping_cart_with_special_offer_TwoForAmount.txt"
        ),
        Arguments.of(
            ShoppingCart()
                .addItem(getToothBrush(), quantity = 4.0)
                .addItem(getToothBrush(), quantity = 1.0),
            "/html_shopping_cart_with_special_offer_TwoForAmountMultipleItems.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getApples(), quantity = 5.0),
            "/html_shopping_cart_with_special_offer_discount_per_kilo.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getRice(), quantity = 2.0),
            "/html_shopping_cart_with_special_offer_discount_per_each.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getToothPaste(), quantity = 5.0),
            "/html_shopping_cart_with_special_offer_FiveForAmount.txt"
        ),
        Arguments.of(
            ShoppingCart().addItem(getCherries(), quantity = 3.0),
            "/html_shopping_cart_with_special_offer_ThreeForAmount.txt"
        ),
        Arguments.of(
            ShoppingCart()
                .addItem(getToothBrush(), quantity = 1.0)
                .addItem(getToothPaste(), quantity = 1.0)
                .addItem(getLemons(), quantity = 1.0),
            "/html_shopping_cart_with_special_offer_PercentDiscountBundle.txt"
        ),
        Arguments.of(
            ShoppingCart()
                .addItem(getOranges(), quantity = 1.0)
                .addItem(getLemons(), quantity = 3.0),
            "/html_shopping_cart_with_special_offer_PercentDiscountBundle_MultipleItems.txt"
        ),
    )

    private fun getToothBrush() = SingleProduct(name = "toothbrush", unit = ProductUnit.Each, unitPrice = 0.99)

    private fun getToothPaste() = SingleProduct(name = "toothpaste", unit = ProductUnit.Each, unitPrice = 1.79)

    private fun getApples() = SingleProduct(name = "apples", unit = ProductUnit.Kilo, unitPrice = 1.99)

    private fun getRice() = SingleProduct(name = "rice", unit = ProductUnit.Each, unitPrice = 2.49)

    private fun getCherries() = SingleProduct(name = "Cherry tomatoes", unit = ProductUnit.Each, unitPrice = 0.69)

    private fun getOranges() = SingleProduct(name = "Oranges", unit = ProductUnit.Kilo, unitPrice = 0.79)

    private fun getLemons() = SingleProduct(name = "Lemons", unit = ProductUnit.Kilo, unitPrice = 1.29)

    private fun ShoppingCart.addItem(item: SingleProduct, quantity: Double): ShoppingCart =
        this.apply {
            addItemQuantity(item, quantity)
        }
}