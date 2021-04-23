package com.microservice.commandesservice.DTO;

public class CommandeDto {

    private String nom;
    private float total;

    public CommandeDto() {
    }

    public String getNom() {
        return nom;
    }

    public float getTotal() {
        return total;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
