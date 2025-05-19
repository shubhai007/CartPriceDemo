package org.shubh.promo;

import org.shubh.cart.Cart;

public interface PromoOffer {

   /** apply a promo offer for products
    * stored in the cart and returb the total price by applying related promo offer*/
   double  applyOffer(Cart cart);
}
