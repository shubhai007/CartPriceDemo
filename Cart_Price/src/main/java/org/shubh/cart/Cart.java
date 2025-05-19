package org.shubh.cart;

import java.util.ArrayList;
import java.util.List;
/**
 * This class represent a cart for items */
public class Cart {

    /** List of products contains by Cart */
    private List<CartProduct> items;

    public Cart() {
        this.items = new ArrayList<CartProduct>();
    }

    public List<CartProduct> getItems() {
        return items;
    }

    /** Used to add product in the cart.
     *
     * @param item */
    public void addItems(CartProduct item) {
        this.items.add(item);
    }

   @Override
    public String toString() {
        return "org.shubh.cart.Cart{" +
                "items=" + items +
                '}';
    }
}
