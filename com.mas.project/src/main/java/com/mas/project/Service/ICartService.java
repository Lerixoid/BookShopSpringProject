package com.mas.project.Service;

import com.mas.project.Model.Book;
import com.mas.project.Model.Cart;
import com.mas.project.Repository.BookRepository;
import com.mas.project.Repository.CartRepository;

import java.util.List;

public interface ICartService {



    List<Book> getAllBooks();
    public void addBookToCart(Book book);
    public void deleteBookfromCart(Book book);
    public Cart getCart();
}
