package org.lessons.java.spring_la_mia_pizzeria_crud.controller.api;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.PizzaModel;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping()
    public ResponseEntity<List<PizzaModel>> index(@RequestParam( name = "name", required = false ) String name) {
        List<PizzaModel> pizzaList;

        if(name != null){
            pizzaList = pizzaService.findByname(name);
        }else{
            pizzaList = pizzaService.findAll();
        }
            return new ResponseEntity<>(pizzaList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaModel> show(@PathVariable Integer id) {
        Optional<PizzaModel> pizza = pizzaService.findById(id);
        if (pizza.isEmpty()) {
            return new ResponseEntity<PizzaModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PizzaModel> store(@RequestBody PizzaModel pizza) {
        return new ResponseEntity<PizzaModel>(pizzaService.createPizza(pizza), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PizzaModel> update(@RequestBody PizzaModel pizza, @PathVariable Integer id) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<PizzaModel>(HttpStatus.NOT_FOUND);

        }
        // setto per garantire che modifichi un id gia esistente
        pizza.setId(id);
        return new ResponseEntity<PizzaModel>(pizzaService.updatePizza(pizza), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PizzaModel> delete(@PathVariable Integer id) {
        if (pizzaService.findById(id).isEmpty()) {
            return new ResponseEntity<PizzaModel>(HttpStatus.NOT_FOUND);

        }
        pizzaService.deleteById(id);
        return new ResponseEntity<PizzaModel>(HttpStatus.OK);
    }

}
