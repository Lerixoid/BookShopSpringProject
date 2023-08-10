package com.mas.project.Controller;


import com.mas.project.Exception.NotFoundException;
import com.mas.project.Model.Book;
import com.mas.project.Model.Cart;
import com.mas.project.Model.Comment;
import com.mas.project.Service.BookService;
import com.mas.project.Service.CartService;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Controller

public class BookController {


    private BookService bookService;
    private CartService cartService;

    public BookController(BookService bookService, CartService cartService) {
        super();
        this.bookService = bookService;
        this.cartService = cartService;
    }

    // build create employee REST API
    @PostMapping()
    public ResponseEntity<Book> saveBook(@RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.saveBook(book), HttpStatus.CREATED);
    }
//
    @GetMapping("/product_page")
    public String productPage(Model model) {
        model.addAttribute("att", bookService.getAllBooks());
        return "product_page";
    }

//main page view
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books";

    }
//cart page view
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("books", cartService.getAllBooks());
        System.out.println(cartService.getCart());
        cartService.recalculateTotalMoney();
        model.addAttribute("shoppingCart", cartService.getCart());
        return "cart";
    }
    //payment method page view
    @GetMapping("/payment_method")
    public String paymentMethod(Model model) {
        model.addAttribute("payment_method", bookService.getAllBooks());
        return "payment_method";
    }
//page view when payment method declined
    @GetMapping("/payment_declined")
    public String paymentDeclined(Model model) {
        model.addAttribute("payment_declined", bookService.getAllBooks());
        return "payment_declined";
    }
//page view when payment accepted
    @GetMapping("/payment_accepted")
    public String PaymentAccepted(Model model) {
        model.addAttribute("payment_declined", bookService.getAllBooks());
        cartService.makeEmptyCart();
        cartService.recalculateTotalMoney();
        return "payment_accepted";
    }

    // build get employee by id REST API
    // http://localhost:8080/api/employees/1
    /*@GetMapping("product_page/{id}")
    public String getBookById(@PathVariable("id") int bookId, Model model) throws NotFoundException {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("product_page",book );
        return "product_page";
    }*/

    @GetMapping("product_page/{id}")
    public String getBookById(@PathVariable("id") int bookId, Model model) throws NotFoundException {
        Book book = bookService.getBookById(bookId);
        Set<Comment> comments = bookService.getComments(book);
        model.addAttribute("product_page", book);
        model.addAttribute("comments", comments);
        return "product_page";
    }
    /*
    @GetMapping("product_page/{id}/comments")
    public String getComments(@PathVariable("id") int bookId, Model model) throws NotFoundException {
        Book book = bookService.getBookById(bookId);

        Set<Comment> comments = new TreeSet<>();
        model.addAttribute("comments",comments );
        return "product_page";
    }*/

    // build update employee REST API
    // http://localhost:8080/api/employees/1
    @PutMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") int id, @RequestBody Book book) {
        return new ResponseEntity<Book>(bookService.updateBook(book, id), HttpStatus.OK);
    }

    // build delete employee REST API
    // http://localhost:8080/api/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") int id) throws NotFoundException {

        // delete employee from DB
        bookService.deleteBook(id);

        return new ResponseEntity<String>("Book deleted successfully!.", HttpStatus.OK);
    }

    @GetMapping({"/buyProduct"})
    public String buyProduct( Model model,@RequestParam(value = "id") int id) throws NotFoundException {
        System.out.println("Hello");
        Book book = bookService.getBookById(id);
        cartService.addBookToCart(book);
        Set<Comment> comments = bookService.getComments(book);
        model.addAttribute("product_page", book);
        model.addAttribute("comments", comments);
        cartService.recalculateTotalMoney();
        return "product_page";
    }
    @GetMapping({"/deleteProduct"})
    public String deleteProduct( Model model,@RequestParam(value = "id") int id) throws NotFoundException {
        System.out.println("Hello");
        Book book = bookService.getBookById(id);
        cartService.deleteBookfromCart(book);
        return "redirect:/cart";
    }

}
