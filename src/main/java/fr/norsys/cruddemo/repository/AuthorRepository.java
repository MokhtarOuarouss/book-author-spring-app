package fr.norsys.cruddemo.repository;

import fr.norsys.cruddemo.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {
    @Query("SELECT a FROM Author a WHERE a.name = :name")
    Optional<Author> findByName(@Param("name") String name);
}
