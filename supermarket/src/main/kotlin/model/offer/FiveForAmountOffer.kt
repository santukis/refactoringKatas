package model.offer

import model.product.SingleProduct

class FiveForAmountOffer(
    product: SingleProduct,
    price: Double
): ItemsForAmountOffer(product, 5, price)