package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import com.mas.project.Helper.BookComparator;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Data
@Entity
@Table(name="Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 8)
    private Long id;

    private String categoryName;

    @OneToMany( cascade = {CascadeType.ALL}, mappedBy = "bookCategory")
    private Set<Book> books = new TreeSet<>(new BookComparator());//{ordered}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Category(){

    }
    public Category(String category, Book book){
        setCategoryName(category);
        addBook(book);
    }
    public Category(String category){
        setCategoryName(category);

    }
    public String getCategory() {
        return categoryName;
    }

    public void setCategoryName(String category) {
        this.categoryName = category;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }
    public void addBook (Book book){
        if(book == null){
            throw new ModelValidationException("Book cant be null");
        }
        if(this.books.contains(book)){
            return;
        }
        this.books.add(book);
        book.setBookCategory(this);
    }
    public void removeBook(Book book){
        if(book == null){
            throw new ModelValidationException("Book cant be null");
        }
        if(this.books.contains(book)){
            if(this.books.size()<2){
                throw new ModelValidationException("cant remove this book");
            }
            this.books.remove(book);
            book.setBookCategory(null);
            return;
        }
        else throw new ModelValidationException("this book doesnt exist in books");

    }
    @Override
    public String toString() {
            return categoryName + " " + getBooks();

    }


}
