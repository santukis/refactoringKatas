package model.product

interface Product {
    val name: String
    val unit: ProductUnit
    val unitPrice: Double
}