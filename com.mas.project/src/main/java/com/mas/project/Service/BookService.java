package com.mas.project.Service;

import com.mas.project.Exception.NotFoundException;
import com.mas.project.Model.Book;
import com.mas.project.Model.Comment;
import com.mas.project.Repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Service
public class BookService implements IBookService{

    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(int id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id:"+  id +  "not found"));
    }

    @Override
    public Book updateBook(Book book, int id) {
        return null;
    }

    @Override
    public void deleteBook(int id) throws NotFoundException {
        bookRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Book with id:"+  id +  "not found"));
        bookRepository.deleteById(id);
    }

    public Set<Comment> getComments(Book book){
       return  bookRepository.findById(book.getId()).get().getComments();
    }

}
