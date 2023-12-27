package com.bittercode.service;

import java.util.List;

import com.bittercode.model.Book;
import com.bittercode.model.StoreException;

public interface SellerBookService {
    public List<Book> getAllBooks() throws StoreException;  // StoreBookServlet

    public String addBook(Book book) throws StoreException;  // AddBookServlet

    public Book getBookById(String bookId) throws StoreException;  // UpdateBookServlet

    public String updateBook(Book book) throws StoreException;  // UpdateBookServlet

    public String deleteBookById(String bookId) throws StoreException;
}
