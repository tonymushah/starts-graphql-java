package mg.tonymushah.starts.graphql.classes;

import java.util.Arrays;
import java.util.List;

public class Author {
    private String id;
    private String firstName;
    private String lastName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Author(String id, String firstName, String lastName) {
        this.setId(id);
        this.setFirstName(firstName);
        ;
        this.setLastName(lastName);
        ;
    }

    public Author() {
    }

    private static List<Author> authors = Arrays.asList(
            new Author("author-1", "Joanne", "Rowling"),
            new Author("author-2", "Herman", "Melville"),
            new Author("author-3", "Anne", "Rice"));

    public static Author getById(String id) {
        return authors.stream().filter(author -> author.getId().equals(id)).findFirst().orElse(null);
    }

    public static List<Author> getAuthors(List<String> ids, int offset, int limit) {
        return authors
                .stream()
                .filter((author) -> ids.contains(author.getId()))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    public static List<Author> getAuthors(int offset, int limit) {
        return authors
                .stream()
                .skip(offset)
                .limit(limit)
                .toList();
    }
}
