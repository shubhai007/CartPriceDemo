package org.shubh.cart;

import org.shubh.promo.PromoOffer;

public class CartService {

    private final PromoOffer promo;
    private final Cart cart;

    public CartService(Cart cart, PromoOffer promo) {
        this.cart = cart;
        this.promo = promo;
    }

    /** Return the final price total after applying the promo. */
    public double getFinalPrice() {
      return  promo.applyOffer(cart);
    }

}
