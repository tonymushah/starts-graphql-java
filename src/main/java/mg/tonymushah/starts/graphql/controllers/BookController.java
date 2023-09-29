package mg.tonymushah.starts.graphql.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import mg.tonymushah.starts.graphql.classes.Author;
import mg.tonymushah.starts.graphql.classes.Book;

@Controller
public class BookController {

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> books(@Argument List<String> ids, @Argument int offset, @Argument int limit) {
        if (ids == null) {
            return Book.getBooks(offset, limit);
        } else if (ids.size() == 0) {
            return Book.getBooks(offset, limit);
        } else {
            return Book.getBooks(ids, offset, limit);
        }
    }
}