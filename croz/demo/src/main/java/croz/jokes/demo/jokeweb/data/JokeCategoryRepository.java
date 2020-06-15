package croz.jokes.demo.jokeweb.data;

import croz.jokes.demo.jokeweb.model.JokeCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Interface for retrieving JokeCategory records from it's database.
 */
public interface JokeCategoryRepository extends CrudRepository<JokeCategory, UUID> {
}
