package org.shubh.promo;

import org.shubh.cart.Cart;
import org.shubh.cart.CartProduct;

import java.util.List;
import java.util.Objects;

/**
 * Buy A and get B for half price. Half price is applied to B items based on the number of A items.
 * */
public class HalfPricePromo implements PromoOffer {

    public double applyOffer(Cart cart) {

        List<CartProduct> items = cart.getItems();

        CartProduct productA = items.stream().filter(item -> item.getName().equals("A")).findFirst().orElse(null);
        CartProduct productB = items.stream().filter(item -> item.getName().equals("B")).findFirst().orElse(null);

        //If Cart have more than 2 product
        double finalPrice = items.stream()
                .filter(item -> !(item.getName().equals("A") || item.getName().equals("B")))
                .mapToDouble(item -> item.getPrice()).sum();

        if (Objects.nonNull(productA)) {
            int quantityA = productA.getQuantity();
            double priceA = productA.getPrice();

            finalPrice += quantityA * priceA;

            if (Objects.nonNull(productB)) {
                int quantityB = productB.getQuantity();
                double priceB = productB.getPrice();

                if (quantityA > quantityB) {
                    finalPrice = finalPrice + (quantityB * priceB) / 2;
                } else {
                    finalPrice = finalPrice + (quantityA * priceB / 2) + (quantityB - quantityA) * priceB;
                }
            }
        } else {
            if (Objects.nonNull(productB)) {
                int quantityB = productB.getQuantity();
                double priceB = productB.getPrice();
                finalPrice += (quantityB * priceB);
            }
        }


        return finalPrice;
    }
}
