package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.IngredienteModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.PizzaModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;
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
@RequestMapping("/ingredienti")
public class IngredientiController {

    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "ingredienti/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredienteRepository.findById(id).get());
        return "ingredienti/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingrediente", new IngredienteModel());
        return "ingredienti/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingrediente") IngredienteModel formIngrediente,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredienti/create-or-edit";
        }
        ingredienteRepository.save(formIngrediente);
        return "redirect:/ingredienti";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingrediente", ingredienteRepository.findById(id).get());
        return "ingredienti/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingrediente") IngredienteModel formIngrediente,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "ingredienti/create-or-edit";
        }
        ingredienteRepository.save(formIngrediente);
        return "redirect:/ingredienti";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        IngredienteModel ingredienteDelete = ingredienteRepository.findById(id).get();
        for(PizzaModel linkedPizza: ingredienteDelete.getPizze()){
            linkedPizza.getIngredienti().remove(ingredienteDelete);
        }

        ingredienteRepository.delete(ingredienteDelete);
        return "redirect:/ingredienti";
    }
}
