package croz.jokes.demo.jokeweb.data;

import croz.jokes.demo.jokeweb.model.Joke;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Interface that extends the CrudRepository interface while
 * providing the method to retrieve all records from the database with
 * in a propagation-friendly manner.
 * @author Leon Kranjcevic
 */
public interface JokeRepository extends CrudRepository<Joke, UUID> {

    /**
     * Method that returns all entries from the database.
     * @param pageable PageRequest (or similar) type specifying the
     *                 order and page separation for the entries.
     * @return
     */
    Page<Joke> findAll(Pageable pageable);
}
