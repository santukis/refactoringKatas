package model

class Teller(private val catalog: SupermarketCatalog) {
    private val offers = HashMap<Product, Offer>()

    fun addSpecialOffer(offerType: SpecialOfferType, product: Product, argument: Double) {
        this.offers[product] = Offer(offerType, product, argument)
    }

    fun createReceiptFrom(theCart: ShoppingCart): Receipt =
        Receipt(catalog).apply {
            addProducts(theCart)
            handleOffers(theCart, offers)
        }
}