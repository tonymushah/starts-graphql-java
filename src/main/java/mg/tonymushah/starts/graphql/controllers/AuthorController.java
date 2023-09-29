package mg.tonymushah.starts.graphql.controllers;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import mg.tonymushah.starts.graphql.classes.Author;
import mg.tonymushah.starts.graphql.classes.Book;
import mg.tonymushah.starts.graphql.repositories.AuthorRepository;
import mg.tonymushah.starts.graphql.repositories.BookRepository;

@Controller
public class AuthorController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public AuthorController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    /*
     * Here we place all @SchemaMapping
     * 
     */

    @SchemaMapping
    public List<Book> books(Author author, @Argument int offset, @Argument int limit) {
        return this.bookRepository.getAuthorBooks(author, offset, limit);
    }

    /*
     * Here we place all @QueryMapping
     * 
     */

    @QueryMapping
    public Author authorById(@Argument String id) {
        return this.authorRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Author> authors(@Argument List<String> ids, @Argument int offset, @Argument int limit) {
        if (ids == null) {
            return this.authorRepository.getAuthors(offset, limit);
        } else if (ids.size() == 0) {
            return this.authorRepository.getAuthors(offset, limit);
        } else {
            return this.authorRepository.getAuthors(ids, offset, limit);
        }
    }

    /*
     * Here we place all @MutationMapping
     * 
     */

    @MutationMapping
    public Author createAuthor(@Argument Author author) {
        return this.authorRepository.save(author);
    }

    @MutationMapping
    public List<Author> deleteAuthors(@Argument List<String> ids) {
        this.bookRepository.deleteBookViaAuthorsIds(ids);
        List<Author> authors = StreamSupport.stream(authorRepository.findAllById(ids).spliterator(), true).toList();
        this.authorRepository.deleteAll(authors);
        return authors;
    }
}
