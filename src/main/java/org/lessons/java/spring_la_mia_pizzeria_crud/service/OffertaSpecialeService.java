package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OffertaSpecialeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertaSpecialeService {

    @Autowired
    private OffertaSpecialeRepository offertaSpecialeRepository;


    
}
