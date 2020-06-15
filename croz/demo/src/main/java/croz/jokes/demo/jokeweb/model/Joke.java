package croz.jokes.demo.jokeweb.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

/**
 * Class that models a singular entry in the joke table of
 * our database. It has unique id, its content, number
 * of likes and dislikes and the difference between them.
 * When added, starting values of likes and dislikes are
 * 1 and 0 respectively.
 * @author Leon Kranjcevic
 */
@Entity
@Table(name = "joke")
public class Joke extends EntityWithUUID {

    private String content;

    private int likes;

    private int dislikes;

    private int difference;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_category"))
    private JokeCategory jokeCategory;

    public Joke() {
        super();
    }

    public Joke(JokeForm jokeForm) {
        this.content = jokeForm.getContent();
        this.jokeCategory = jokeForm.getCategory();
        this.likes = 1;
        this.dislikes = 0;
        this.difference = likes-dislikes;
    }

    public String getContent() {
        return content;
    }

    public JokeCategory getJokeCategory() {
        return jokeCategory;
    }

    public int getLikes() {
        return likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public int getDifference() {
        return likes-dislikes;
    }

    /**
     * Method that increases the like counter by 1
     * and updates the difference of likes and dislikes.
     */
    public void incrementLike() {
        likes += 1;
        difference = getDifference();
    }

    /**
     * Method that increases the dislike counter by 1
     * and updates the difference of likes and dislikes.
     */
    public void incrementDislike() {
        dislikes += 1;
        difference = getDifference();
    }

    /**
     * Two Joke objects are equal when their ids are identical.
     * @param o Object to compare to
     * @return true if ids are the same, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke = (Joke) o;
        return this.getId() == joke.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }


}
