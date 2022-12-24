package model.offer

import model.product.SingleProduct

class FiveForAmountOffer(
    product: SingleProduct,
    price: Double
): ItemsForAmountOffer(
    product = product,
    items = 5,
    amount =  price
)