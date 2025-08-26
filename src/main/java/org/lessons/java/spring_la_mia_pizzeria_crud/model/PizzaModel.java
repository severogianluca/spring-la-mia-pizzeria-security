package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "pizzas")
public class PizzaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nome")
    @NotBlank(message = "Name is required")
    private String nome;

    @Column(name = "descrizione")
    @NotBlank(message = "Description is required")
    private String descrizione;

    @Column(name = "foto_url")
    @NotBlank(message = "Image URL is required")
    private String fotoUrl;

    @NotNull(message = "Price is required")
    @Min(value = 0, message = "Price cannot be negative")
    @Column(name = "prezzo")
    private BigDecimal prezzo;

    @OneToMany(mappedBy = "pizza", cascade = { CascadeType.ALL})
    private List<OffertaSpecialeModel> offerteSpeciali;

    @ManyToMany
    @JoinTable(
        name = "ingrediente_pizza",
        joinColumns = @JoinColumn(name = "pizza_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
        
    )
    private List<IngredienteModel> ingredienti;


    public PizzaModel() {
    }


    public List<OffertaSpecialeModel> getOfferteSpeciali() {
        return offerteSpeciali;
    }

    public void setOfferteSpeciali(List<OffertaSpecialeModel> offerteSpeciali) {
        this.offerteSpeciali = offerteSpeciali;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public String toString() {
        return String.format("%s %s %s€", nome, descrizione, prezzo);
    }

    public List<IngredienteModel> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<IngredienteModel> ingredienti) {
        this.ingredienti = ingredienti;
    }

    

}
