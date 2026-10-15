package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Paiement;

import java.util.List;

public interface IPaiementService {
    Paiement ajouterPaiement(Paiement paiement);
    Paiement modifierPaiement(Paiement paiement);
    Paiement getPaiement(long id);
    void supprimerPaiementById(Long id);
    List<Paiement> getAllPaiements();
}
