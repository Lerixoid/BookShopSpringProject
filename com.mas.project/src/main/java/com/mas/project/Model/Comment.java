package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Entity
@Table(name="Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Content of comment is mandatory")
    @Column(name = "content", nullable = false, columnDefinition="LONGTEXT")
    @Size(min = 2, max = 100)
    private String content;

    @NotBlank(message = "date of book is mandatory")
    @Column(name = "date", nullable=false)
    private LocalDate dateAdded;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "book_id" )
    private Book book;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "user_id" )
    private RegisteredUser user;

    @Max(5)
    @NotBlank(message = "Description of book is mandatory")
    private int starPoints;

    @ManyToOne
    Rate rate;

    public Comment(String content, LocalDate dateAdded,RegisteredUser user, Book book) {
        setContent(content);
        setDateAdded(dateAdded);
        setUser(user);
        setBook(book);
    }

    public Comment() {

    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if(content==null){
            throw new ModelValidationException("content cant be null");
        }
        this.content = content;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        if(dateAdded==null){
            throw new ModelValidationException("date cant be null");
        }
        this.dateAdded = dateAdded;
    }

    public Book getBook() {
        return book;
    }

    private void setBook(Book book) {
        if (book == null) {
            throw new ModelValidationException("book cant be null");
        }
        this.book = book;
        book.setComment(this);
    }

    public void remove() {
        if (this.book != null) {
            Book tmp = this.book;
            this.book = null;
            tmp.removeComment(this);
        }
    }

    public RegisteredUser getUser() {
        return this.user;
    }

    public void setUser(RegisteredUser user) {
        if (user == null) {
            throw new ModelValidationException("user cant be null");
        }
        this.user = user;
    }
    public int getStarPoints() {
        return starPoints;
    }

    public void setStarPoints(int starPoints) {
        if(starPoints>5){
            throw new ModelValidationException("star points cant be nigger than 5");
        }
        this.starPoints = starPoints;
    }
    public String getUserFullname(){
        return user.getName() + " " + user.getSurname();
    }
    public Rate getRate() {
        return this.rate;
    }

    public void setRate(Rate rate) {
        if (rate == null) {
            throw new ModelValidationException("rate cant be null");
        }
        this.rate = rate;
    }


    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                ", dateAdded=" + dateAdded +
                ", book=" + book.getTitle();
    }
}
