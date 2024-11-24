package com.sis324.ecomerce.services;

import com.sis324.ecomerce.models.Cart;
import com.sis324.ecomerce.models.Product;
import com.sis324.ecomerce.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    public Cart addProductToCart(Long userId, Product product) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            cart = new Cart();
            cart.setUser(null); // Se puede asociar al usuario correspondiente.
        }
        List<Product> products = cart.getProducts();
        products.add(product);
        cart.setProducts(products);

        // Calcular el precio total
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        cart.setTotalPrice(total);

        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(Long userId, Product product) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            List<Product> products = cart.getProducts();
            products.remove(product);
            cart.setProducts(products);

            // Recalcular el precio total
            double total = products.stream().mapToDouble(Product::getPrice).sum();
            cart.setTotalPrice(total);

            cartRepository.save(cart);
        }
        return cart;
    }

    public void clearCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart != null) {
            cart.getProducts().clear();
            cart.setTotalPrice(0.0);
            cartRepository.save(cart);
        }
    }
}
