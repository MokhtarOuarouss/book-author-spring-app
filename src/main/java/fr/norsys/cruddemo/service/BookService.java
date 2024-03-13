package fr.norsys.cruddemo.service;

import fr.norsys.cruddemo.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

        public Book saveBook(Book book);
        public List<Book> getAllBooks();
        public Optional<Book> getBookById(int  id);
        public void DeleteBook(int id);

    
}
