package edu.icet.service;

import edu.icet.dto.Cart;

import java.util.List;

public interface CartService {
    void addBookToCart(Cart cart);
    List<Cart> getAllCartItemsByUserId(String id);
    void removeBookFromCart(Cart cart);
}
