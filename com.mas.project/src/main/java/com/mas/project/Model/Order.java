package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @OneToOne
    @JoinColumn(name = "cart_id" )
    private Cart cart;

    @ManyToOne(cascade={CascadeType.MERGE})
    @JoinColumn(name = "user_id" )
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private RegisteredUser user;

    @NotBlank(message = "Date of order of book is mandatory")
    private LocalDate orderDate;

    private double totalValue;


    public Order(){

    }
    public Order(Cart cart, RegisteredUser user, LocalDate orderDate, double totalValue) {

        setCart(cart);
        setUser(user);
        setOrderDate(orderDate);
        setTotalValue(totalValue);
    }

    public RegisteredUser getUser() {
        return user;
    }

    public void setUser(RegisteredUser user) {
        if(user!=null){
            this.user=user;
        }
    }
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        if(cart!=null){
            this.cart=cart;
        }
    }
    public void removeCart(Cart cart){
        this.cart = null;
    }

    public double getTotalValue() {
        return cart.getTotalMoney();
    }

    public void setTotalValue(double totalValue) {
        if(totalValue==0.0){
            throw new ModelValidationException("total value cant be null");
        }
        this.totalValue = totalValue;
    }
    public void calculateTotalValue(){
        this.totalValue = cart.getTotalMoney();
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        if(orderDate==null){
            throw new ModelValidationException("order date cant be null");
        }
        this.orderDate = orderDate;
    }
    public void remove() {
        if (this.cart != null) {
            Cart tmp = this.cart;
            this.cart = null;
            tmp.removeOrder();
        }
    }
}
