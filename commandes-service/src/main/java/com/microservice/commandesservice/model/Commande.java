package com.microservice.commandesservice.model;


import com.microservice.articlesservice.model.Article;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Commande {

    @Id
    private int id;
    private String nom;
    private float total;

    @ManyToMany
    private List<Article> articleList;

    public Commande() {
    }

    public Commande(int id, String nom, float total, List<Article> articleList) {
        this.id = id;
        this.nom = nom;
        this.total = total;
        this.articleList = articleList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }
}
