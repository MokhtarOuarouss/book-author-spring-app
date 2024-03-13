package fr.norsys.cruddemo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id ;
    @NotBlank(message = "Name is mandatory")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    @NotNull
    private float price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Constructors
    public Book(String title, Author author, float price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Getter methods
    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    // Setter methods (if needed)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setPrice(float price) {
        this.price = price;
    }




}
