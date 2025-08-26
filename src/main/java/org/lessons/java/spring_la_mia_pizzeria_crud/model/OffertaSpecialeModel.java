package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerte_speciali")
public class OffertaSpecialeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pizza_id", nullable = false)
    private PizzaModel pizza;

    @NotBlank(message = "Insert a title")
    private String title;

    @NotNull(message = "The special offer cannot be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startSpecialOffer;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "The special offer cannot be null")
    private LocalDate finishSpecialOffer;

    public Integer getId() {
        return id;
    }

    public OffertaSpecialeModel(){
        
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PizzaModel getPizza() {
        return pizza;
    }

    public void setPizza(PizzaModel pizza) {
        this.pizza = pizza;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStartSpecialOffer() {
        return startSpecialOffer;
    }

    public void setStartSpecialOffer(LocalDate startSpecialOffer) {
        this.startSpecialOffer = startSpecialOffer;
    }

    public LocalDate getFinishSpecialOffer() {
        return finishSpecialOffer;
    }

    public void setFinishSpecialOffer(LocalDate finishSpecialOffer) {
        this.finishSpecialOffer = finishSpecialOffer;
    }



    
}
