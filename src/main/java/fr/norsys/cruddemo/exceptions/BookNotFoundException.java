package fr.norsys.cruddemo.exceptions;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int  bookId) {
        super("Product not found with ID:  "+ bookId);
    }
}
