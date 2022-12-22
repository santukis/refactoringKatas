package model.offer

import model.product.SingleProduct

class TwoForAmountOffer(
    product: SingleProduct,
    price: Double
): ItemsForAmountOffer(product, 2, price)