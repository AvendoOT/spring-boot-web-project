package croz.jokes.demo.jokeweb.model;

import javax.persistence.*;

/**
 * Class that models a joke category entry
 * to out database. Its contents are
 * specified by administrator's input.
 * @author Leon Kranjcevic
 */
@Entity
@Table(name = "joke_category")
public class JokeCategory extends EntityWithUUID {

    /**
     * Category of a given given
     */
    private String category;

    public JokeCategory() {
        super();
    }

    public JokeCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
