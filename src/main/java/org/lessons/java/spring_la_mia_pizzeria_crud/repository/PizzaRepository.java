package org.lessons.java.spring_la_mia_pizzeria_crud.repository;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.PizzaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<PizzaModel, Integer> {

    public List<PizzaModel> findByNomeContaining(String nome);
}