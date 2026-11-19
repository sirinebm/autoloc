package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Employe;

import java.util.List;

public interface IEmployeService {
    Employe ajouterEmploye(Employe employe);
    Employe modifierEmploye(Employe employe);
    Employe getEmploye(long id);
    void supprimerEmployeById(Long id);
    List<Employe> getAllEmployes();

    Employe affecterEmployeAgence(Long idEmploye, Long idAgence);

    Employe desaffecterEmployeAgence(Long idEmploye);

    Employe desaffecterEmploye(Long idEmploye);

    Employe affecterEmploye(Long idEmploye, Long idAgence);
}
