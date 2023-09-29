package mg.tonymushah.starts.graphql.repositories;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import jakarta.transaction.Transactional;
import mg.tonymushah.starts.graphql.classes.Author;

/**
 * AuthorRepository
 */
@Transactional
public interface AuthorRepository extends CrudRepository<Author, String>, PagingAndSortingRepository<Author, String> {
    default List<Author> getAuthors(int offset, int limit) {
        return StreamSupport
                .stream(this.findAll().spliterator(), true)
                .filter((author) -> author != null)
                .skip(offset)
                .limit(limit)
                .toList();
    }
    default List<Author> getAuthors(List<String> ids, int offset, int limit){
        return StreamSupport
                .stream(this.findAllById(ids).spliterator(), true)
                .filter((author) -> author != null)
                .skip(offset)
                .limit(limit)
                .toList(); 
    }
}