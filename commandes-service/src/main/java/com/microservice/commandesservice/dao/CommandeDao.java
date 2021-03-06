package com.microservice.commandesservice.dao;

import com.microservice.commandesservice.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommandeDao extends JpaRepository<Commande,Integer> {

    Commande findById(int id);
}
