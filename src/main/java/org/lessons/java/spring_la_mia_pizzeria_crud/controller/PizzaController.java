package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.OffertaSpecialeModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.PizzaModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredienteRepository;

import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;
    @Autowired
    private IngredienteRepository ingredienteRepository;

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/pizzas")
    public String getListaPizzas(Authentication authentication, Model model) {
        List<PizzaModel> pizzas = pizzaService.findAll();

        model.addAttribute("pizzas", pizzas);
        model.addAttribute("username", authentication.getName());

        return "pizzas/index";
    }

    @GetMapping("/pizzas/{id}")
    public String getPizzaById(@PathVariable("id") int id, Model model) {

        Optional<PizzaModel> pizza = pizzaService.findById(id);
        if (pizza.isPresent()) {
            model.addAttribute("pizza", pizza.get());
            model.addAttribute("ingredienti", ingredienteRepository.findAll());

            return "pizzas/show";
        }

        return "redirect:pizzas";
    }

    @GetMapping("/search")
    public String searchTitle(@RequestParam String nome, Model model) {
        List<PizzaModel> pizzas = pizzaService.findByname(nome);
        model.addAttribute("pizzas", pizzas);
        return "pizzas/index";
    }

    @GetMapping("/pizzas/create")
    public String create(Model model) {
        model.addAttribute("pizza", new PizzaModel());
        model.addAttribute("ingredienti", ingredienteRepository.findAll());
        return "pizzas/create";
    }

    @PostMapping("/pizzas/create")
    public String store(@Valid @ModelAttribute("pizza") PizzaModel formPizza, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            return "pizzas/create";
        }
        pizzaService.save(formPizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.findById(id));
        model.addAttribute("ingredienti", ingredienteRepository.findAll());

        return "pizzas/edit";
    }

    @PostMapping("/pizzas/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") PizzaModel formPizza, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredienti", ingredienteRepository.findAll());
            return "pizzas/edit";
        }
        pizzaService.save(formPizza);
        return "redirect:/pizzas";
    }

    @PostMapping("pizzas/delete/{id}")
    public String delete(@PathVariable Integer id) {
        pizzaService.deleteById(id);
        return "redirect:/pizzas";
    }

    @GetMapping("/pizzas/{id}/offerta")
    public String offerta(@PathVariable Integer id, Model model) {
        OffertaSpecialeModel offertaSpeciale = new OffertaSpecialeModel();

        PizzaModel pizza = pizzaService.findById(id).get();
        offertaSpeciale.setPizza(pizza);

        model.addAttribute("offertaSpeciale", offertaSpeciale);
        model.addAttribute("pizza", pizza);
        return "offertaSpeciale/create";
    }
}
