package com.info.gestion_stock.models;

import lombok.AllArgsConstructor;

import java.util.List;

public class Alert {
    private List<Produit> rouge;
    private List<Produit> orange;

    public Alert(List<Produit> rouge, List<Produit> orange) {
        this.rouge = rouge;
        this.orange = orange;
    }

    public List<Produit> getRouge() {
        return rouge;
    }

    public void setRouge(List<Produit> rouge) {
        this.rouge = rouge;
    }

    public List<Produit> getOrange() {
        return orange;
    }

    public void setOrange(List<Produit> orange) {
        this.orange = orange;
    }
}
