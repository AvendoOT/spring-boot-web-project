package croz.jokes.demo.jokeweb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Class that models an object generated when
 * user adds a new joke. It only has values that
 * user can input, so no like and dislike property.
 * When initialized, it performs a for validation checks for
 * its properties.
 * @author Leon Kranjcevic
 */
public class JokeForm {

    /**
     * Form validation that specifies that content field cannot be empty
     * and that it can only consist of text characters specified by given
     * regular expression.
     */
    @NotBlank(message = "Joke is required")
    @Pattern(regexp = "^[a-zA-Z ?!',.]*$", message = "Only text characters")
    private String content;

    /**
     * Category field must be selected.
     * User cannot submit a joke without specifying
     * the category it belongs to.
     */
    @NotNull
    private JokeCategory category;

    public JokeForm(String content, JokeCategory category)  {
        this.content = content;
        this.category = category;
    }

    public JokeForm() {
        super();
    }

    public String getContent() {
        return content;
    }

    public JokeCategory getCategory() {
        return category;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCategory(JokeCategory jc) {
        this.category = jc;
    }

}
