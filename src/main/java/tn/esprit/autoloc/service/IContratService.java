package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Contrat;

import java.util.List;

public interface IContratService {
    Contrat ajouterContrat(Contrat contrat);
    Contrat modifierContrat(Contrat contrat);
    Contrat getContrat(long id);
    void supprimerContratById(Long id);
    List<Contrat> getAllContrats();
}
