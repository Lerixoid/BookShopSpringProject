package com.mas.project.Service;

import com.mas.project.Model.Book;
import com.mas.project.Model.Cart;
import com.mas.project.Repository.BookRepository;
import com.mas.project.Repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//For this example we will have only one cart with id 1 on web-page
@Service
public class CartService implements ICartService {
    CartRepository cartRepository;
    Cart cart = new Cart();
    public CartService(CartRepository cartRepository) {
        super();
        this.cartRepository = cartRepository;
    }
    @Override
    public List<Book> getAllBooks() {
        Cart cart = cartRepository.getReferenceById(1L);
         return cart.getBooks();
    }
    public void addBookToCart(Book book) {
        Cart cart = cartRepository.getReferenceById(1L);
        cart.addBook(book);
        cartRepository.save(cart);
    }
    public void deleteBookfromCart(Book book) {
        Cart cart = cartRepository.getReferenceById(1L);
        cart.removeBook(book);
        cartRepository.save(cart);
    }
    public Cart getCart(){
        Cart cart = cartRepository.getReferenceById(1L);
        return cart;
    }
    public void recalculateTotalMoney(){
        Cart cart = cartRepository.getReferenceById(1L);
        cart.calculateTotalMoney();
        System.out.println(cart.getTotalMoney());
        cartRepository.save(cart);
    }
    public void makeEmptyCart() {
        Cart cart = cartRepository.getReferenceById(1L);
        if (cart.getBooks() != null) {
            cart.removeAllBooks();
            System.out.println("Cart" + cart);
            if(cart!=null){
                System.out.println("Not null");
                cart.removeAllBooks();
            }
        }

        cartRepository.save(cart);

    }
}
