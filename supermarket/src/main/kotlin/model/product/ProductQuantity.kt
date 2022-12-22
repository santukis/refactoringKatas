package model.product

class ProductQuantity(
    val product: Product,
    val quantity: Double
) {

    fun updateQuantity(quantity: Double): ProductQuantity =
        ProductQuantity(
            product = product,
            quantity = this.quantity + quantity)

    fun getTotalPrice(): Double = product.unitPrice * quantity
}