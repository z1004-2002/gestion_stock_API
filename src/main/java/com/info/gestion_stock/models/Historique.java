package com.info.gestion_stock.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Historique")
public class Historique {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String nature;
    private String description;
    private Integer qte;
    private LocalDate date;
    @ManyToOne
    private Produit produit;
    @ManyToOne
    private Magasin magasin;

    public Historique(String nature, String description, Integer qte, LocalDate date, Produit produit,Magasin magasin) {
        this.nature = nature;
        this.description = description;
        this.qte = qte;
        this.date = date;
        this.produit = produit;
        this.magasin = magasin;
    }
    public Historique(String nature, String description, Integer qte, LocalDate date, Produit produit) {
        this.nature = nature;
        this.description = description;
        this.qte = qte;
        this.date = date;
        this.produit = produit;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }
}
