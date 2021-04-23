package com.microservice.commandesservice.web.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.microservice.commandesservice.dao.CommandeDao;
import com.microservice.commandesservice.web.exceptions.CommandeIntrouvableException;
import io.swagger.annotations.ApiOperation;
import com.microservice.commandesservice.model.Commande;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CommandeController {
    @Autowired
    private CommandeDao commandeDao;

    @Autowired
    private HttpServletRequest requestContext ;

    private static final Logger logger = LoggerFactory.getLogger(CommandeController.class);

    @ApiOperation(value = "Récupérer toutes les commandes")
    @RequestMapping(value = "/Commandes", method = RequestMethod.GET)
    public MappingJacksonValue listeCommandes() {
        List<Commande> commandes = commandeDao.findAll();

        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("total", "id");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("MonFiltreDynamique", monFiltre);

        MappingJacksonValue commandesFiltres = new MappingJacksonValue(commandes);

        commandesFiltres.setFilters(listDeNosFiltres);
        ;
        logger.info("Début d'appel au service Commandes pour la requête : " + requestContext.getHeader("req-id"));

        return commandesFiltres;
    }
    //Récupérer une commande par son Id
    @ApiOperation(value = "Récupérer une commande grâce à son ID à condition que celui-ci soit en stock!")
    @GetMapping(value = "/Commandes/id/{id}")
    public MappingJacksonValue afficherUneCommande(@PathVariable int id) throws CommandeIntrouvableException {
        Commande commande = commandeDao.findById(id);
        if (commande == null) {
            throw new CommandeIntrouvableException("La Commande avec l'id " + id + " est INTROUVABLE");
        }
        SimpleBeanPropertyFilter monFiltre = SimpleBeanPropertyFilter.serializeAllExcept("total", "id");
        FilterProvider listDeNosFiltres = new SimpleFilterProvider().addFilter("MonFiltreDynamique", monFiltre);

        MappingJacksonValue commandesFiltres = new MappingJacksonValue(commande);

        commandesFiltres.setFilters(listDeNosFiltres);

        return commandesFiltres;
    }

}
