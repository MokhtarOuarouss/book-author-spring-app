package fr.norsys.cruddemo.service;

import fr.norsys.cruddemo.entity.Book;
import fr.norsys.cruddemo.exceptions.BookNotFoundException;
import fr.norsys.cruddemo.exceptions.InvalidAuthorException;
import fr.norsys.cruddemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository ;
    @Override
    public Book saveBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getName() == null || book.getAuthor().getName().isEmpty()) {
            throw new InvalidAuthorException("Author name cannot be empty.");
        }
        return  bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(int id) {

        Optional<Book> retrievedBook =  bookRepository.findById(id);
        if (retrievedBook.isEmpty()) {
            throw new BookNotFoundException(id);
        }
        return retrievedBook;

    }

    @Override
    public void DeleteBook(int id) {
        Optional<Book> deletedBook = bookRepository.findById(id);
        if (deletedBook.isEmpty()) {
            throw new BookNotFoundException(id);
        }
        bookRepository.delete(deletedBook.get());
    }

}
