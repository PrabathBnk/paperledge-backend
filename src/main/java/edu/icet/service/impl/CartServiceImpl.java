package edu.icet.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.dto.Book;
import edu.icet.dto.Cart;
import edu.icet.dto.User;
import edu.icet.entity.BookEntity;
import edu.icet.entity.CartEntity;
import edu.icet.entity.UserEntity;
import edu.icet.repository.CartRepository;
import edu.icet.service.BookService;
import edu.icet.service.CartService;
import edu.icet.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository repository;
    private final ObjectMapper mapper;
    private final UserService userService;
    private final BookService bookService;

    @Override
    public void addBookToCart(Cart cart) {
        CartEntity cartEntity = mapper.convertValue(cart, CartEntity.class);
        cartEntity.setUser(mapper.convertValue(userService.getUserById(cart.getUser().getId()), UserEntity.class));
        cartEntity.setBook(mapper.convertValue(bookService.getBookById(cart.getBook().getId()), BookEntity.class));

        CartEntity existingCartItem = getCartItem(cart);
        if (existingCartItem != null) {
            cartEntity.setQuantity(cart.getQuantity() + existingCartItem.getQuantity());
            
            cartEntity.setId(existingCartItem.getId());
        }
        repository.save(cartEntity);
    }

    @Override
    public List<Cart> getAllCartItemsByUserId(String id) {
        Iterable<CartEntity> cartEntityIterable = repository.findAllByUserId(id);
        List<Cart> cartItemList = new ArrayList<>();

        cartEntityIterable.forEach(cartEntity -> {
            Cart cart = mapper.convertValue(cartEntity, Cart.class);
            cart.setUser(mapper.convertValue(cartEntity.getUser(), User.class));
            cart.setBook(mapper.convertValue(cartEntity.getBook(), Book.class));
            cartItemList.add(cart);
        });

        return cartItemList;
    }

    @Override
    public void removeBookFromCart(Cart cart) {
        repository.deleteCartItem(cart.getUser().getId(), cart.getBook().getId());
    }

    public CartEntity getCartItem(Cart cart) {
        return repository.findByUserIdAndBookId(cart.getUser().getId(), cart.getBook().getId()).orElse(null);
    }
}
