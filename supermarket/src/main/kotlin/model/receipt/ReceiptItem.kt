package model.receipt

import model.discount.Discount
import model.product.ProductQuantity

class ReceiptItem(
    val products: List<ProductQuantity>,
    val discounts: List<Discount>
) {

}