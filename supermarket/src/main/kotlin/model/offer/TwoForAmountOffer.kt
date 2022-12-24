package model.offer

import model.product.SingleProduct

class TwoForAmountOffer(
    product: SingleProduct,
    price: Double
): ItemsForAmountOffer(
    product = product,
    items = 2,
    amount = price
)