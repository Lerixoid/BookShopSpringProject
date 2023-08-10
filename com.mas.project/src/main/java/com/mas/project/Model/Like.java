package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private boolean isLiked;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "user_id" )
    private RegisteredUser user;

    @EqualsAndHashCode.Exclude @ToString.Exclude
    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "book_id" )
    private Book book;

    public boolean isLiked() {
        return isLiked;
    }

    public Like(){

    }
    public Like(Book book, RegisteredUser user, boolean isLiked){
        setLiked(isLiked);
        setBook(book);
        setUser(user);
    }
    public void setLiked(boolean liked) {
        isLiked = liked;
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
    public Book getBook() {
        return book;
    }

    private void setBook(Book book) {
        if (book == null) {
            throw new ModelValidationException("book cant be null");
        }
        this.book = book;
        book.setLike(this);
    }

    public void remove() {
        if (this.book != null) {
            Book tmp = this.book;
            this.book = null;
            tmp.removeLike(this);
        }
        if (this.user != null) {
            RegisteredUser tmp = this.user;
            this.user = null;
            tmp.removeLike(this);
        }
    }
}
