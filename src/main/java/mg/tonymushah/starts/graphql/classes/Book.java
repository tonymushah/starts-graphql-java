package mg.tonymushah.starts.graphql.classes;

import java.util.Arrays;
import java.util.List;

public class Book {

    private String id;
    private String name;
    private int pageCount;
    private String authorId;

    public Book(String id, String name, int pageCount, String authorId) {
        this.setId(id);
        this.setName(name);
        this.setPageCount(pageCount);
        this.setAuthorId(authorId);
    }

    private static List<Book> books = Arrays.asList(
            new Book("book-1", "Harry Potter and the Philosopher's Stone", 223, "author-1"),
            new Book("book-2", "Moby Dick", 635, "author-2"),
            new Book("book-3", "Interview with the vampire", 371, "author-3"));

    public static Book getById(String id) {
        return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public static List<Book> getAuthorBooks(Author author, int offset, int limit) {
        return books
                .stream()
                .filter((book) -> book.getAuthorId().equals(author.getId()))
                .skip(offset)
                .limit(limit)
                .toList();
    }
    public static List<Book> getBooks(List<String> ids, int offset, int limit){
        return books
                .stream()
                .filter((book) -> ids.contains(book.getId()))
                .skip(offset)
                .limit(limit)
                .toList();
    }
    public static List<Book> getBooks(int offset, int limit){
        return books
                .stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }
}