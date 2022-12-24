package model.product

data class ProductQuantity(
    val product: Product,
    val quantity: Double
) {

    fun updateQuantity(quantity: Double): ProductQuantity = copy(quantity = this.quantity + quantity)

    fun getTotalPrice(): Double = product.unitPrice * quantity
}