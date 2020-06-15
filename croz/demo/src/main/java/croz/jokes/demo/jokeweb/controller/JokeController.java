package croz.jokes.demo.jokeweb.controller;

import croz.jokes.demo.jokeweb.data.JokeCategoryRepository;
import croz.jokes.demo.jokeweb.data.JokeRepository;
import croz.jokes.demo.jokeweb.model.Joke;
import croz.jokes.demo.jokeweb.model.JokeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

/**
 * Controller class which implements the basic functionality of the web application.
 * @author Leon Kranjcevic
 */
@Controller
@RequestMapping("")
public class JokeController {

    @Autowired
    private JokeRepository jokeRepository;

    @Autowired
    private JokeCategoryRepository jokeCategoryRepository;

    /**
     * Method that initializes the main page of the application (located at localhost:8080/).
     * It produces sorted list (difference of likes and dislikes) of user given jokes.
     * Propagation is assigned to show 10 results per page.
     * @param model model for preparing HTTP data
     * @param page current page (propagation)
     * @return starting page (localhost:8080/)
     */
    @GetMapping("/")
    public String displayAllJokes(Model model, @RequestParam(defaultValue = "0") int page) {

        model.addAttribute("jokes",jokeRepository.findAll(
                PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "difference"))));
        return "index";
    }

    /**
     * Method that renders a page through which user can add a new joke.
     * User inputs a text content of a joke and selects it's category.
     * New category cannot be added.
     * @param model model that carries JokeForm form validation properties and list of categories from database
     * @return page which allows user to add a new joke at localhost:8080/new
     */
    @GetMapping("new")
    public String renderAddJokeForm(Model model) {

        model.addAttribute("jokeForm", new JokeForm());
        model.addAttribute("categories", jokeCategoryRepository.findAll());
        return "create";
    }

    /**
     * Method that adds a new joke to the database.
     * @param jokeForm user given joke
     * @param errors if form validation produced exceptions
     * @param model
     * @return if there where no errors, user is returned to the starting page at localhost:8080/, otherwise
     *          an error is displayed.
     */
    @PostMapping("new")
    public String createJoke(@ModelAttribute @Valid JokeForm jokeForm, BindingResult errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("categories", jokeCategoryRepository.findAll());
            return "create";
        }
        jokeForm.setCategory(jokeForm.getCategory());
        jokeForm.setContent(jokeForm.getContent());
        jokeRepository.save(new Joke(jokeForm));
        return "redirect:";
    }

    /**
     * Method that increments the like counter of a joke object from database.
     * When like is selected, page automatically refreshes the counter while staying
     * at the same page.
     * @param id UUID of a selected entry
     * @param page current page
     * @return the same page as before updating the database entry
     */
    @PostMapping("/updateLike")
    public String incrementLike(@RequestParam UUID id, @RequestParam int page) {

        Joke updatedJoke = jokeRepository.findById(id).get();
        updatedJoke.incrementLike();
        jokeRepository.save(updatedJoke);
        StringBuilder sb = new StringBuilder("redirect:/?page=");
        sb.append(page);
        return sb.toString();
    }

    /**
     * Method that increments the dislike counter of a joke object from database.
     * When dislike is selected, page automatically refreshes the counter while staying
     * at the same page.
     * @param id UUID of a selected entry
     * @param page current page
     * @return the same page as before updating the database entry
     */
    @PostMapping("/updateDislike")
    public String incrementDislike(@RequestParam UUID id, @RequestParam int page) {

        Joke updatedJoke = jokeRepository.findById(id).get();
        updatedJoke.incrementDislike();
        jokeRepository.save(updatedJoke);
        StringBuilder sb = new StringBuilder("redirect:/?page=");
        sb.append(page);
        return sb.toString();
    }


}
