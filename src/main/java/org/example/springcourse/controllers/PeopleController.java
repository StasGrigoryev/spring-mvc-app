package org.example.springcourse.controllers;

import org.example.springcourse.dao.PersonDAO;
import org.example.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping() //mapped to "/people" because we've mapped class to "/people"
    public String index(Model model) {
        // Retrieving all people from DAO and passing it to View
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    //when user chooses a user this method called
    //we obtain id from the url (using @PathVariable("id")) and retrieving a user with this id
    //using show() method
    public String show(@PathVariable("id") int id, Model model) {
        //Retrieving one person from DAO by id and passing it to the View
        model.addAttribute("person", personDAO.show(id));
        return "people/show";
    }

    //getting form to create new person
    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    //using @ModelAttribute annotation to let spring create new person
    //with parameters received from user via http post method
    //after object creation and initializing of its fields
    //spring puts it into the model
    @PostMapping()
    public String create(@ModelAttribute("person")
                             @Valid Person person,
                                BindingResult br) {
        if (br.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person")
                         @Valid Person person,
                         BindingResult br,
                         @PathVariable("id") int id) {
        if (br.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
