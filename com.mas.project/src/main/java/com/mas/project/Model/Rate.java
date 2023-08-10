package com.mas.project.Model;


import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 8)
    private int id;

    @OneToMany()
    List<Comment> comments;

    @OneToOne()
    Book book;

    private double ratepoints = 0;

    public Rate(){

    }
    public double getRatepoints(){
        List<Integer> points = new ArrayList();
        for(int i=0;i< comments.size();i++){
            points.set(i, comments.get(i).getStarPoints());
        }
        int sum = (points.stream().
                mapToInt(i -> i.intValue()).sum());

         return (sum/5)*100;

    }

    public void setRatepoints(double ratepoints) {
       this.ratepoints = ratepoints;
    }

    public Book getBook() {
        return this.book;
    }

    public void setBook(Book book) {
        if (book == null) {
            throw new ModelValidationException("book cant be null");
        }
        this.book = book;
        book.setRate(this);
    }
    public List<Comment> getComments() {
        return Collections.unmodifiableList(comments);
    }

    public void setComment(Comment comment) {
        if(comment ==null){
            throw new ModelValidationException("comment cant be null");
        }
        if(this.comments.contains(comment)){
            return;
        }
        if(comment.getRate()!=this){
            throw new ModelValidationException("comment is not related to this rate");
        }
        this.comments.add(comment);
    }
}
