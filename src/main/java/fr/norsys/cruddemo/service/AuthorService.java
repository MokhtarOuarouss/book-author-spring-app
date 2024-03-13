package fr.norsys.cruddemo.service;

import fr.norsys.cruddemo.entity.Author;
import fr.norsys.cruddemo.exceptions.InvalidAuthorException;
import fr.norsys.cruddemo.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    AuthorRepository authorRepository ;
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author saveAuthor(Author author) {
        if (author == null ||author.getName() == null ) {
            throw new InvalidAuthorException("Author name cannot be empty.");
        }
        return  authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Optional<Author> getAuthorById(int id) {
        Optional<Author> author = authorRepository.findById(id);;
        if (author.isEmpty()){
            throw new InvalidAuthorException("Author id not exist  : "+id);
        }
        return author;
    }

    public Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByName(name);
    }

    public void DeleteAuthor(int id) {
        Optional<Author> authorDeleted = authorRepository.findById(id);

        if (authorDeleted.isEmpty()) {
            throw new InvalidAuthorException("Author id not exist  : "+id);
        }
        authorRepository.delete(authorDeleted.get());
    }
}
