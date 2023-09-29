package mg.tonymushah.starts.graphql.controllers;

import java.util.List;

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
public class BookController {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    
    public BookController(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    /*
     * Here we place all @SchemaMapping
     * 
     */

    @SchemaMapping
    public Author author(Book book) {
        if(book.getAuthorId() != null){
            return this.authorRepository.findById(book.getAuthorId()).orElse(null);
        }else{
            return null;
        }
    }

    /*
     * Here we place all @QueryMapping
     * 
     */

    @QueryMapping
    public Book bookById(@Argument String id) {
        return this.bookRepository.findById(id).orElse(null);
    }

    @QueryMapping
    public List<Book> books(@Argument List<String> ids, @Argument int offset, @Argument int limit) {
        if (ids == null) {
            return this.bookRepository.getBooks(offset, limit);
        } else if (ids.size() == 0) {
            return this.bookRepository.getBooks(offset, limit);
        } else {
            return this.bookRepository.getBooks(ids, offset, limit);
        }
    }

    /*
     * Here we place all @MutationMapping
     * 
     */
    @MutationMapping
    public Book createBook(@Argument Book book){
        return this.bookRepository.save(book);
    }
}