package tn.esprit.autoloc.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;
    private final AgenceRepository agenceRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        vehicule.setAgence(recupererAgenceExistante(vehicule));
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule modifierVehicule(Vehicule vehicule) {
        vehicule.setAgence(recupererAgenceExistante(vehicule));
        return vehiculeRepository.save(vehicule);
    }

    @Override
    public Vehicule getVehicule(long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerVehiculeById(Long id) {
        if (reservationRepository.existsByVehicule_IdVehicule(id)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Impossible de supprimer le véhicule " + id + " : des réservations sont encore rattachées."
            );
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    private Agence recupererAgenceExistante(Vehicule vehicule) {
        if (vehicule.getAgence() == null
            || vehicule.getAgence().getIdAgence() == null
            || vehicule.getAgence().getIdAgence() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le champ agence.idAgence est obligatoire et doit être un id existant (jamais 0)."
            );
        }

        Long idAgence = vehicule.getAgence().getIdAgence();
        return agenceRepository.findById(idAgence)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Agence introuvable avec idAgence = " + idAgence
            ));
    }
}
