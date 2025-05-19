package org.shubh.cart;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.shubh.promo.CheapestFreePromo;
import org.shubh.promo.HalfPricePromo;
import org.shubh.promo.PromoOffer;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(value = TestInstance.Lifecycle.PER_CLASS)
class CartServiceTest {
    private CartProduct cartProductX;
    private CartProduct cartProductY;
    private CartProduct cartProductZ;
    private CartProduct cartProductR;

    private CartProduct cartProductA;
    private CartProduct cartProductB;

    @BeforeAll
    public void setup() {

        cartProductX = new CartProduct("X", 10, 1);
        cartProductY = new CartProduct("Y", 5, 1);
        cartProductZ = new CartProduct("Z", 4, 1);
        cartProductR = new CartProduct("R", 2, 1);

        cartProductA = new CartProduct("A", 10, 1);
        cartProductB = new CartProduct("B", 9, 2);

    }

    @Test
    public void testGetFinalPrice_HalfPricePromo() {

        Cart cart = new Cart();

        PromoOffer promoOffer = new HalfPricePromo();

        cart.addItems(cartProductA);
        cart.addItems(cartProductB);

        CartService cartService = new CartService(cart, promoOffer);
        double actualFinalPrice = cartService.getFinalPrice();
        assertEquals(23.50, actualFinalPrice);
    }

    @Test
    public void testGetFinalPrice_CheapestFreePromo() {

        Cart cart = new Cart();

        List<CartProduct> cartProducts = Arrays.asList(cartProductX, cartProductR, cartProductY, cartProductZ);

        PromoOffer promoOffer = new CheapestFreePromo();

        cart.addItems(cartProductX);
        cart.addItems(cartProductY);
        cart.addItems(cartProductZ);
        cart.addItems(cartProductR);

        CartService cartService = new CartService(cart, promoOffer);

        double actualFinalPrice = cartService.getFinalPrice();
        assertEquals(17.0, actualFinalPrice);


    }

    @Test
    public void testGetFinalPrice_WithoutProduct() {

        Cart cart = new Cart();

        PromoOffer promoOffer = new HalfPricePromo();

        CartService cartService = new CartService(cart, promoOffer);
        double actualFinalPrice = cartService.getFinalPrice();
        assertEquals(0.0, actualFinalPrice);
    }


}