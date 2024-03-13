package fr.norsys.cruddemo.controller;


import fr.norsys.cruddemo.entity.Author;
import fr.norsys.cruddemo.entity.Book;
import fr.norsys.cruddemo.exceptions.BookNotFoundException;
import fr.norsys.cruddemo.service.AuthorService;
import fr.norsys.cruddemo.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("*")

public class BookController {

    @Autowired
    private BookService bookService ;
    @Autowired
    private AuthorService authorService ;

    @PostMapping
    public ResponseEntity<String>  CreateBook(@RequestBody @Valid Book book) {


        Optional<Author> existingAuthor = authorService.getAuthorByName(book.getAuthor().getName());

        if (existingAuthor.isEmpty()) {
            Author newAuthor = new Author();
            newAuthor.setName(book.getAuthor().getName());
            authorService.saveAuthor(newAuthor);
            book.setAuthor(newAuthor);
        } else {
            book.setAuthor(existingAuthor.get());
        }

        bookService.saveBook(book);
        return ResponseEntity.ok("Book Created successfully");
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Book>> getBookById(@PathVariable int  id){
        Optional<Book> book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBook(@PathVariable int id, @RequestBody Book updatedBook) throws Exception {
        Optional<Book> existingBookOptional = bookService.getBookById(id);

        if (existingBookOptional.isPresent()) {
            Book existingBook = existingBookOptional.get();

            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthor() != null) {
                existingBook.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getPrice() != 0) {
                existingBook.setPrice(updatedBook.getPrice());
            }

            // Save the updated book
            this.CreateBook(existingBook);
            return ResponseEntity.ok(null);

        } else {
            throw new BookNotFoundException(id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
         bookService.DeleteBook(id);
         return ResponseEntity.ok(null);

    }



}
