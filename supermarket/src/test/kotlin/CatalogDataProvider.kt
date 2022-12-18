import model.*
import model.catalog.DefaultCatalog
import model.catalog.SupermarketCatalog
import model.offer.*
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object CatalogDataProvider {

    fun createCatalog(): SupermarketCatalog =
        DefaultCatalog().apply {
            addProduct(product = getToothBrush(), price = 0.99)
            addProduct(product = getToothPaste(), price = 1.79)
            addProduct(product = getApples(), price = 1.99)
            addProduct(product = getRice(), price = 2.49)
            addProduct(product = getCherries(), price = 0.69)
        }

    fun SupermarketCatalog.addSpecialOffers() {
        addOffer(TwoForAmountOffer(product = getToothBrush(), argument = 0.99))
        addOffer(PercentDiscountOffer(product = getApples(), argument = 20.0))
        addOffer(PercentDiscountOffer(product = getRice(), argument = 10.0))
        addOffer(FiveForAmountOffer(product = getToothPaste(), argument = 7.49))
        addOffer(ThreeForTwoOffer(product = getCherries()))
    }

    @JvmStatic
    fun getShoppingCartWithoutSpecialOffers(): Stream<Arguments> = Stream.of(
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
    fun getShoppingCartWithSpecialOffers(): Stream<Arguments> = Stream.of(
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
    )

    private fun getToothBrush(): Product = Product(name = "toothbrush", unit = ProductUnit.Each)

    private fun getToothPaste(): Product = Product(name = "toothpaste", unit = ProductUnit.Each)

    private fun getApples(): Product = Product(name = "apples", unit = ProductUnit.Kilo)

    private fun getRice(): Product = Product(name = "rice", unit = ProductUnit.Each)

    private fun getCherries(): Product = Product(name = "Cherry tomatoes", unit = ProductUnit.Each)

    private fun ShoppingCart.addItem(item: Product, quantity: Double): ShoppingCart =
        this.apply {
            addItemQuantity(item, quantity)
        }
}