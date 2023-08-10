package com.mas.project.Model;

import com.mas.project.Exception.ModelValidationException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Data
@Entity
@Table(name="Book")
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Size(min = 1, max = 8)
    private int id;

    @NotBlank(message = "Title of book is mandatory")
    @Column(name = "title", nullable = false)
    @Size(min = 2, max = 100)
    private String title;

    @ManyToOne()
    @JoinColumn(name = "publisher_id")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Publisher publisher;

    @NotBlank(message = "Description of book is mandatory")
    @Size(min = 2, max = 1000)
    @Column(name = "description", nullable=false,columnDefinition="LONGTEXT")

    private String description;

    @NotBlank(message = "Publish date of book is mandatory")
    @Column(name = "publishDate", nullable=false)
    private LocalDate publishDate;

    @Min(0)
    private double price;

    @Enumerated(EnumType.STRING)
    private BookRating rating;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude @ToString.Exclude
    private Category bookCategory;

    private String imgUrl;

    @Max(5)
    @NotBlank(message = "Description of book is mandatory")
    private int starPoints;

    public Book(String title){
        this.title = title;
    }

    private static List<Book> extent = new ArrayList<>();

    @OneToMany(cascade =  {CascadeType.MERGE, CascadeType.ALL}, mappedBy = "book")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne()
    @JoinColumn(name = "cart_id")
    private Cart cartId;

    @OneToMany(cascade =  {CascadeType.MERGE, CascadeType.ALL}, mappedBy = "book")
    private List<Like> likes;

    @OneToOne
    private Rate rate;

    public Book() {

    }
    public Book(String title,Publisher publisher, LocalDate publishDate, String description,
                BookRating rating, Category bookCategory, String imgUrl, int starPoints) {

        setTitle(title);
        setPublisher(publisher);
        setDescription(description);
        setPublishDate(publishDate);
        setRating(rating);
        setBookCategory(bookCategory);
        setImgUrl(imgUrl);
        setStarPoints(starPoints);
        extent.add(this);

    }
    public Book(String title,Publisher publisher, LocalDate publishDate, String description, BookRating rating,
                Double price,Category bookCategory,String imgUrl, int starPoints) {

        setTitle(title);
        setPublisher(publisher);
        setDescription(description);
        setPublishDate(publishDate);
        setRating(rating);
        setPrice(price);
        setBookCategory(bookCategory);
        setImgUrl(imgUrl);
        setStarPoints(starPoints);
        extent.add(this);
    }
    public int getId() {
        return id;
    }

    public static List<Book> setExtent(){
        return Collections.unmodifiableList(extent);
    }

    public static void saveExtent() throws IOException {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("src/main/resources/static/Book.ser"))) {
            output.writeObject(extent);
        }
    }
    public static void loadExtent() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("src/main/resources/static/Book.ser"))) {
            extent = (List<Book>) input.readObject();
        }
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title==null || title.isBlank()){
            throw new ModelValidationException("title cant be empty");
        }
        this.title = title;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        if (publishDate==null){
            throw new ModelValidationException("publish date cant be null");
        }
        if(true == publishDate.isAfter(LocalDate.now())){
            throw new ModelValidationException("publish date is invalid");
        }
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description==null || description.isBlank()) {
            throw new ModelValidationException("description cant be empty");
        }
        this.description = description;
    }


    public BookRating getRating() {
        return rating;
    }

    public void setRating(BookRating rating) {
        if (rating==null) {
            throw new ModelValidationException("rating cant be empty");
        }
        this.rating = rating;
    }


    public int getBookAge(){
        if (publishDate==null) {
            return 0;
        }
        LocalDate now = LocalDate.now();
        Period period = publishDate.until(now);
        int year = period.getYears();
        return year;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        if(price== null && price < 0){
            throw new ModelValidationException("price cant be null or negative");
        }
        if(this.price!=0&& price>this.price){
            throw new ModelValidationException("you cant increase price change");
        }
        this.price = price;
    }

    public Publisher getPublisher() {
        return this.publisher;
    }

    public String getPublisherFullname() {

        return this.publisher.getName() + " " + this.publisher.getSurname();
    }
    public void setPublisher(Publisher publisher) {
        if (this.publisher == publisher){
            return;
        }

        //no category, but we will set it
        if (this.publisher == null && publisher!=null ){
            addPublisher(publisher);
        }
        //category set, and we want to remove it
        else if(this.publisher!=null && publisher==null){
            deletePublisher();
        }
        //category set, and another category is passed
        else if (this.publisher!=null && publisher!=null){
            deletePublisher();
            addPublisher(publisher);
        }
    }
    private void addPublisher (Publisher publisher){
        this.publisher = publisher;
        publisher.addBook(this);
    }
    private void deletePublisher () {
        Publisher toDelete = this.publisher;
        this.publisher = null;
        toDelete.removeBook(this);
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        if(imgUrl==null){
            throw new ModelValidationException("image url cant be null");
        }
        this.imgUrl = imgUrl;
    }

    public Category getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(Category bookCategory) {
        if (this.bookCategory == bookCategory){
            return;
        }

        //no category, but we will set it
        if (this.bookCategory == null && bookCategory!=null ){
            addBookCategory(bookCategory);
        }
        //category set, and we want to remove it
        else if(this.bookCategory!=null && bookCategory==null){
            deleteBookCategory();
        }
        //category set, and another category is passed
        else if (this.bookCategory!=null && bookCategory!=null){
            deleteBookCategory();
            addBookCategory(bookCategory);
        }
    }
    private void addBookCategory (Category bookCategory){
        this.bookCategory = bookCategory;
        bookCategory.addBook(this);
    }
    private void deleteBookCategory () {
        Category toDelete = this.bookCategory;
        this.bookCategory = null;
        toDelete.removeBook(this);
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
        if(comment.getBook()!=this){
            throw new ModelValidationException("coment is not related to this book");
        }
        this.comments.add(comment);
    }

    public void addComment(String content,LocalDate date, RegisteredUser user) {
        Comment tmp = new Comment(content, date, user, this);
        this.comments.add(tmp);
    }

    public void removeComment(Comment comment) {
        if (this.comments.contains(comment)) {
            this.comments.remove(comment);
            comment.remove();
        }
    }
    public Cart getCart() {
        return cartId;
    }

    public void setShippingCart(Cart cart) {

        if (this.cartId == cart){
            return;
        }

        //no category, but we will set it
        if (this.cartId == null && cart!=null ){
            addShippingCart(cart);
        }
        //category set, and we want to remove it
        else if(this.cartId!=null && cart==null){
            deleteShippingCart();
        }
        //category set, and another category is passed
        else if (this.cartId!=null && cart!=null){
            deleteShippingCart();
            addShippingCart(cart);
        }
    }
    private void addShippingCart (Cart cart){
        this.cartId = cart;
        cart.addBook(this);
    }
    private void deleteShippingCart () {
        Cart toDelete = this.cartId;
        this.cartId = null;
        toDelete.removeBook(this);
    }
    @Override
    public String toString() {
        return '\n' + "Book: " +
                title + '\n' +
                "PublishDate: " + publishDate ;
    }

    public List<Like> getLikes() {
        return Collections.unmodifiableList(likes);
    }

    public void setLike(Like like) {
        if(like ==null){
            throw new ModelValidationException("like cant be null");
        }
        if(this.likes.contains(like)){
            return;
        }
        if(like.getBook()!=this){
            throw new ModelValidationException("like is not related to this book");
        }
        this.likes.add(like);
    }

    public void addLike(RegisteredUser user) {
        Like tmp = new Like(this, user, true);
        this.likes.add(tmp);
    }

    public void removeLike(Like like) {
        if (this.likes.contains(like)) {
            this.likes.remove(like);
            like.remove();
        }
    }

    public Rate  getRate(){
        return this.rate;
    }

    public void setRate(Rate rate){
        if(rate==null){
            throw new ModelValidationException("rate cant be null");
        }
        this.rate = rate;
    }

}