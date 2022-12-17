package model

class ShoppingCart {

    private val items = ArrayList<ProductQuantity>()
    internal var productQuantities: MutableMap<Product, Double> = HashMap()


    internal fun getItems(): List<ProductQuantity> {
        return ArrayList(items)
    }

    internal fun addItem(product: Product) {
        this.addItemQuantity(product, 1.0)
    }

    internal fun productQuantities(): Map<Product, Double> {
        return productQuantities
    }


    fun addItemQuantity(product: Product, quantity: Double) {
        items.add(ProductQuantity(product, quantity))
        if (productQuantities.containsKey(product)) {
            productQuantities[product] = productQuantities[product]!! + quantity
        } else {
            productQuantities[product] = quantity
        }
    }
}