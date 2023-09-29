package mg.tonymushah.starts.graphql.repositories;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.transaction.Transactional;
import mg.tonymushah.starts.graphql.classes.Author;
import mg.tonymushah.starts.graphql.classes.Book;

@Transactional
public interface BookRepository extends CrudRepository<Book, String>, PagingAndSortingRepository<Book, String> {

    default List<Book> getAuthorBooks(Author author, int offset, int limit) {
        return StreamSupport
                .stream(this.findAll().spliterator(), true)
                .filter((book) -> book.getAuthorId().equals(author.getId()))
                .skip(offset)
                .limit(limit)
                .toList();
    }

    default List<Book> getBooks(int offset, int limit) {
        return StreamSupport
                .stream(this.findAll().spliterator(), true)
                .filter((book) -> book != null)
                .skip(offset)
                .limit(limit)
                .toList();
    }
    default List<Book> getBooks(List<String> ids, int offset, int limit){
        return StreamSupport
                .stream(this.findAllById(ids).spliterator(), true)
                .filter((book) -> book != null)
                .skip(offset)
                .limit(limit)
                .toList();
    }
    default List<Book> deleteBookViaAuthorsIds(List<String> ids){
        
    }
}
