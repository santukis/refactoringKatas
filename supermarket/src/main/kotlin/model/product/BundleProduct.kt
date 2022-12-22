package model.product

class BundleProduct(
    val bundle: List<Product>
): Product {

    override val name: String
        get() = bundle.joinToString() { it.name }

    override val unit: ProductUnit
        get() = ProductUnit.Bundle

    override val unitPrice: Double
        get() = bundle.sumOf { it.unitPrice }
}