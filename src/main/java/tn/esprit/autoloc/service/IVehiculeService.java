package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Vehicule;

import java.util.List;

public interface IVehiculeService {
    Vehicule ajouterVehicule(Vehicule vehicule);
    Vehicule modifierVehicule(Vehicule vehicule);
    Vehicule getVehicule(long id);
    void supprimerVehiculeById(Long id);
    List<Vehicule> getAllVehicules();
}
