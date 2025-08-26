package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.OffertaSpecialeModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.PizzaModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OffertaSpecialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/offerta")
public class OffertaSpecialeController {

    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepo;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offertaSpeciale") OffertaSpecialeModel formOfferta,
            BindingResult bindingResult,
            Model model) {
        model.addAttribute("pizza", formOfferta.getPizza());

        if (bindingResult.hasErrors()) {
            return "offertaSpeciale/create";
        }
        offertaSpecialeRepo.save(formOfferta);

        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        OffertaSpecialeModel offertaSpeciale = offertaSpecialeRepo.findById(id).get();
        PizzaModel pizza = offertaSpeciale.getPizza();

        model.addAttribute("offertaSpeciale", offertaSpecialeRepo.findById(id).get());

        model.addAttribute("pizza", pizza);

        return "offertaSpeciale/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") OffertaSpecialeModel formOfferta, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "offerta/edit";
        }
        offertaSpecialeRepo.save(formOfferta);
        return "redirect:/pizzas/" + formOfferta.getPizza().getId();
    }

    // @PostMapping("/delete/{id}")
    // public String delete(@PathVariable Integer id, OffertaSpecialeModel formOfferta) {
    //     offertaSpecialeRepo.deleteById(id);
    //     return "redirect:/pizzas" + formOfferta.getPizza().getId();
    // }

    // Nel tuo OffertaSpecialeController.java

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        OffertaSpecialeModel offertaToDelete = offertaSpecialeRepo.findById(id).get();
        Integer pizzaId = offertaToDelete.getPizza().getId();

        offertaSpecialeRepo.deleteById(id);

        return "redirect:/pizzas/" + pizzaId;
    }

}
