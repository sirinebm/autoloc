package tn.esprit.autoloc.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;

    @Override
    public Agence ajouterAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence modifierAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public Agence getAgence(long id) {
        return agenceRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerAgenceById(Long id) {
        if (vehiculeRepository.existsByAgence_IdAgence(id)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Impossible de supprimer l'agence " + id + " : des véhicules sont encore rattachés."
            );
        }
        agenceRepository.deleteById(id);
    }

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence ajouterAgenceEtAffecterVehicules(Agence agence) {
        List<Vehicule> vehicules = agence.getVehicules();
        agence.setVehicules(new ArrayList<>());
        Agence agenceSauvegardee = agenceRepository.save(agence);

        if (vehicules != null) {
            List<Vehicule> vehiculesSauvegardes = new ArrayList<>();
            for (Vehicule vehicule : vehicules) {
                vehicule.setAgence(agenceSauvegardee);
                vehiculesSauvegardes.add(vehiculeRepository.save(vehicule));
            }
            agenceSauvegardee.setVehicules(vehiculesSauvegardes);
        }

        return agenceSauvegardee;
    }
}
