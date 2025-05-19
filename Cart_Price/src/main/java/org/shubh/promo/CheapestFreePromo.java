package org.shubh.promo;

import org.shubh.cart.Cart;
import org.shubh.cart.CartProduct;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Buy any 3 items from a set of products {X, Y, Z, P, Q} and get the cheapest one for free.
 * **/
public class CheapestFreePromo implements PromoOffer {

    private List<String> offerProductNames;

    public CheapestFreePromo() {
        this.offerProductNames = Arrays.asList("X", "Y", "Z", "P", "Q");
          }
    
    public double applyOffer(Cart cart) {
        List<CartProduct> cartProducts = cart.getItems();
        Map<Boolean, List<CartProduct>> map = cartProducts.stream()
                .collect(Collectors.partitioningBy(p -> offerProductNames.contains(p.getName())));

        List<CartProduct> offerProducts = map.get(true);
        List<CartProduct> withoutOfferProducts = map.get(false);

        double finalPrice = withoutOfferProducts
                .stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        if (offerProducts.size() < 3) {
            finalPrice += offerProducts
                    .stream()
                    .mapToDouble(p -> p.getPrice() * p.getQuantity())
                    .sum();
        } else {

            Comparator<CartProduct> compareByPrice = Comparator.comparing(CartProduct::getPrice);
            List<CartProduct> sortedOfferProducts = offerProducts.stream().sorted(compareByPrice).collect(Collectors.toList());

            for (int i = 1; i < sortedOfferProducts.size(); i++) {

                CartProduct product = sortedOfferProducts.get(i);
                finalPrice += product.getPrice() * product.getQuantity();
            }

        }
        return finalPrice;
    }
}
