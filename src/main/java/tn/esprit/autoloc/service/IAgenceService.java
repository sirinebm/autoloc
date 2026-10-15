package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Agence;

import java.util.List;

public interface IAgenceService {
    Agence ajouterAgence(Agence agence);
    Agence modifierAgence(Agence agence);
    Agence getAgence(long id);
    void supprimerAgenceById(Long id);
    List<Agence> getAllAgences();
}
