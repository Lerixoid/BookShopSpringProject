package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name="Publisher")
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 8)
    private int id;

    private String name;

    private String surname;

    @OneToMany( cascade = {CascadeType.ALL}, mappedBy = "publisher")
    private List<Book> books;

    public Publisher(){

    }
    public Publisher(String name, String surname){
        setName(name);
        setSurname(surname);
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        if (name==null){
            throw new ModelValidationException("name cant be null");
        }
    }
    public String getSurname(){
        return this.surname;
    }

    public void setSurname(String surname){
        if (surname==null){
            throw new ModelValidationException("surname cant be null");
        }
    }
    public List<Book> getBooks(){
        return  Collections.unmodifiableList(books);
    }

    public void addBook (Book book){
        if(book == null){
            throw new ModelValidationException("Book cant be null");
        }
        if(this.books.contains(book)){
            return;
        }
        this.books.add(book);
        book.setPublisher(this);
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
            book.setPublisher(null);
            return;
        }
        else throw new ModelValidationException("this book doesnt exist in books");

    }

}
