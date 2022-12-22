package model.product

data class SingleProduct(
    override val name: String,
    override val unit: ProductUnit,
    override val unitPrice: Double = 0.0
) : Product
