package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Maintenance;

import java.util.List;

public interface IMaintenanceService {
    Maintenance ajouterMaintenance(Maintenance maintenance);
    Maintenance modifierMaintenance(Maintenance maintenance);
    Maintenance getMaintenance(long id);
    void supprimerMaintenanceById(Long id);
    List<Maintenance> getAllMaintenances();
}
