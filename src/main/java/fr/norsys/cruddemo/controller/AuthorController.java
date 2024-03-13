package fr.norsys.cruddemo.controller;

import fr.norsys.cruddemo.entity.Author;
import fr.norsys.cruddemo.exceptions.InvalidAuthorException;
import fr.norsys.cruddemo.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService ;

    @PostMapping
    public ResponseEntity<String> CreateAuthor(@RequestBody @Valid Author author){

        authorService.saveAuthor(author);
        return ResponseEntity.ok("Author created successfully");
    }
    @GetMapping
    public List<Author> getAllAuthor(){
        return authorService.getAllAuthors();

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Author>> getAuthorById(@PathVariable int  id){

        return ResponseEntity.ok(authorService.getAuthorById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAuthor(@PathVariable int id, @RequestBody Author updatedAuthor) throws Exception {

        if (updatedAuthor.getName() == null || updatedAuthor.getName().isEmpty()) {
            throw new InvalidAuthorException("Author name cannot be empty.");
        }

        Optional<Author> existingAuthorOptional = authorService.getAuthorById(id);
        if (existingAuthorOptional.isPresent()) {
            Author existingAuthor = existingAuthorOptional.get();

            if (updatedAuthor.getName() != null) {
                existingAuthor.setName(updatedAuthor.getName());
                authorService.saveAuthor(existingAuthor);
            }

        } else {
            throw new InvalidAuthorException("Author id not exist  : "+id);
        }
        return ResponseEntity.ok(null);
    }


    
}
