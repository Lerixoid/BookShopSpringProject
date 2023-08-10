package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.*;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name="RegisteredUser")
@Inheritance(strategy =  InheritanceType.JOINED)
public class RegisteredUser extends Person {


    @NotBlank(message = "login  is mandatory")
    @Column(name = "login", nullable = false)
    @Size(min = 2, max = 100)
    private String login;

    @NotBlank(message = "password  is mandatory")
    @Column(name = "password", nullable = false)
    @Size(min = 2, max = 100)
    private String password;

    @NotBlank(message = "email  is mandatory")
    @Column(name = "email", nullable = false)
    @Size(min = 2, max = 100)
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    private Adress adress;

    @NotBlank(message = "name  is mandatory")
    @Column(name = "name", nullable = false)
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank(message = "surname  is mandatory")
    @Column(name = "surname", nullable = false)
    @Size(min = 2, max = 100)
    private String surname;

    @OneToMany(cascade =  {CascadeType.MERGE, CascadeType.ALL}, mappedBy = "user")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade =  {CascadeType.MERGE, CascadeType.ALL}, mappedBy = "user")
    private Set<Like> wishlist = new HashSet<>();;

    @OneToMany(cascade =  {CascadeType.MERGE, CascadeType.ALL}, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();

    public RegisteredUser(){

    }

    private static List<RegisteredUser> extent = new ArrayList<>();

    public static List<RegisteredUser> setExtent(){
        return Collections.unmodifiableList(extent);
    }

    public static void saveExtent() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/static/RegisteredUser.ser"))) {
            output.writeObject(extent);
        }
    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/static/RegisteredUser.ser"))) {
            extent = (List<RegisteredUser>) input.readObject();
        }
    }
    public Set<Comment> getComments() {
        return Collections.unmodifiableSet(comments);
    }

    public void setComment(Comment comment) {
        if(comment ==null){
            throw new ModelValidationException("comment cant be null");
        }
        if(this.comments.contains(comment)){
            return;
        }
        if(comment.getUser()!=this){
            throw new ModelValidationException("coment is not related to this book");
        }
        this.comments.add(comment);
    }

    public void addComment(String content, LocalDate date, Book book) {
        Comment tmp = new Comment(content, date, this, book);
        this.comments.add(tmp);
    }

    public void removeComment(Comment comment) {
        if (this.comments.contains(comment)) {
            this.comments.remove(comment);
            comment.remove();
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login==null || login.isBlank()){
            throw new ModelValidationException("login cant be empty");
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password==null || password.isBlank()){
            throw new ModelValidationException("password cant be empty");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email==null || email.isBlank()){
            throw new ModelValidationException("email cant be empty");
        }
        this.password = email;
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
    public Adress getAdress() {
        return adress;
    }

    public void setAdress(String country, String city,String street, String postalcode) {
        this.adress = new Adress(country,city, street,postalcode);
    }
    public Set<Like> getLikes() {
        return Collections.unmodifiableSet(wishlist);
    }

    public void setLike(Like like) {
        if(like == null){
            throw new ModelValidationException("like cant be null");
        }
        if(this.wishlist.contains(like)){
            return;
        }
        if(like.getUser()!=this){
            throw new ModelValidationException("like is not related to this user");
        }
        this.wishlist.add(like);
    }

    public void addLike(Book book) {
        Like tmp = new Like(book, this, true);
        this.wishlist.add(tmp);
    }

    public void removeLike(Like like) {
        if (this.wishlist.contains(like)) {
            this.wishlist.remove(like);
            like.remove();
        }
    }
    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public void setOrder(Order order) {
        if(order == null){
            throw new ModelValidationException("order cant be null");
        }
        if(this.orders.contains(order)){
            return;
        }
        if(order.getUser()!=this){
            throw new ModelValidationException("order is not related to this user");
        }
        this.orders.add(order);
    }

    public void addOrder(Cart cart) {
        Order tmp = new Order(cart, this ,LocalDate.now(), cart.getTotalMoney());
        this.orders.add(tmp);
    }

    public void removeOrder(Order order) {
        if (this.orders.contains(order)) {
            this.orders.remove(order);
            order.remove();
        }
    }

}
