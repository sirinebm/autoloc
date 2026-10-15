package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Equipement;

import java.util.List;

public interface IEquipementService {
    Equipement ajouterEquipement(Equipement equipement);
    Equipement modifierEquipement(Equipement equipement);
    Equipement getEquipement(long id);
    void supprimerEquipementById(Long id);
    List<Equipement> getAllEquipements();
}
