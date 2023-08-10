package com.mas.project.Service;

import com.mas.project.Exception.NotFoundException;
import com.mas.project.Model.Book;
import com.mas.project.Model.Comment;

import java.util.List;
import java.util.Set;

public interface IBookService {
    Book saveBook(Book employee);
    List<Book> getAllBooks();
    Set<Comment> getComments(Book book);
    Book getBookById(int id) throws NotFoundException;
    Book updateBook(Book employee, int id);
    void deleteBook(int id) throws NotFoundException;
}
