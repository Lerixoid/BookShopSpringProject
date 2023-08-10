package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity
@Table(name="Cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    private double totalMoney;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "cartId")
    private List<Book> books = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "order_id" )
    private Order order;

    public Cart() {

    }
    public Cart(double totalMoney, List<Book> bookList ){
        setTotalMoney(totalMoney);
        setBooks(books);
    }

    public double getTotalMoney() {
       return this.totalMoney;
    }

    public void calculateTotalMoney(){
        this.totalMoney =   books.stream().map(x->x.getPrice()).reduce(0.0, Double::sum);
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(this.books);
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public  List<Book> getBooksList(){

       return books;
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new ModelValidationException("book cant be null");
        }
        if (this.books.contains(book)) {
            return;
        }
        this.books.add(book);
        book.setShippingCart(this);
    }

    public void removeBook(Book book){
        if (book == null){
            throw new ModelValidationException("book cant be null");

        }
        if (this.books.contains(book)) {
            this.books.remove(book);
            book.setShippingCart(null);
        }
    }
    public void removeAllBooks(){
        if(this.books!=null) {

            for(int i = 0;i<books.size();i++ ){
                System.out.println("book:" +  books.get(i));
                removeBook(books.get(i));
            }

        }
    }
    public void removeOrder () {
        Order toDelete = this.order;
        this.order = null;
        toDelete.removeCart(this);
    }

}
