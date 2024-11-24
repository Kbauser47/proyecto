package com.sis324.ecomerce.controllers;

import com.sis324.ecomerce.models.Cart;
import com.sis324.ecomerce.models.Product;
import com.sis324.ecomerce.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCartByUser(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/{userId}/add")
    public Cart addProductToCart(@PathVariable Long userId, @RequestBody Product product) {
        return cartService.addProductToCart(userId, product);
    }

    @PostMapping("/{userId}/remove")
    public Cart removeProductFromCart(@PathVariable Long userId, @RequestBody Product product) {
        return cartService.removeProductFromCart(userId, product);
    }

    @PostMapping("/{userId}/clear")
    public void clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
    }
}
