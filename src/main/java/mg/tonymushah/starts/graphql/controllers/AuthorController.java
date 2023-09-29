package mg.tonymushah.starts.graphql.controllers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import mg.tonymushah.starts.graphql.classes.Author;
import mg.tonymushah.starts.graphql.classes.Book;

@Controller
public class AuthorController {
    @SchemaMapping
    public List<Book> books(Author author, @Argument int offset, @Argument int limit) {
        return Book.getAuthorBooks(author, offset, limit);
    }

    @QueryMapping
    public Author authorById(@Argument String id) {
        return Author.getById(id);
    }

    @QueryMapping
    public List<Author> authors(@Argument List<String> ids, @Argument int offset, @Argument int limit) {
        if (ids == null) {
            return Author.getAuthors(offset, limit);
        } else if (ids.size() == 0) {
            return Author.getAuthors(offset, limit);
        } else {
            return Author.getAuthors(ids, offset, limit);
        }
    }
}
