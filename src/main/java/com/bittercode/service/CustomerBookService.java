package com.bittercode.service;

import java.util.List;

import com.bittercode.model.Book;
import com.bittercode.model.StoreException;

public interface CustomerBookService {
    public List<Book> getAllBooks() throws StoreException;  // BuyBooksServlet, ReceiptServlet, ViewBookServlet

    public String addBook(Book book) throws StoreException;  // BuyBooksServlet, ProcessPaymentServlet, ViewBookServlet

    public List<Book> getBooksByCommaSeperatedBookIds(String commaSeperatedBookIds) throws StoreException;  // CartServlet

    public String updateBookQtyById(String bookId, int quantity) throws StoreException; // ProcessPaymentServlet, ReceiptServlet
}
