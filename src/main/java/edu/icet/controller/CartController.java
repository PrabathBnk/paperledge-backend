package edu.icet.controller;

import edu.icet.dto.Cart;
import edu.icet.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
@RequiredArgsConstructor
public class CartController {

    private final CartService service;

    @PostMapping
    public void addBook(@RequestBody Cart cart){
        service.addBookToCart(cart);
    }

    @GetMapping("/all")
    public List<Cart> getAllByUserId(@RequestParam("id") String id){
        return service.getAllCartItemsByUserId(id);
    }

    @DeleteMapping
    public void removeBook(@RequestBody Cart cart){
        service.removeBookFromCart(cart);
    }
}
