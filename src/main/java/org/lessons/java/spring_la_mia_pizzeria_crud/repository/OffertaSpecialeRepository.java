package org.lessons.java.spring_la_mia_pizzeria_crud.repository;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.OffertaSpecialeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffertaSpecialeRepository extends JpaRepository<OffertaSpecialeModel, Integer>{
    
}
