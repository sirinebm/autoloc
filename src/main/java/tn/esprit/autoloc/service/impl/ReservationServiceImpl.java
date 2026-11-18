package tn.esprit.autoloc.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.repository.VehiculeRepository;
import tn.esprit.autoloc.service.IReservationService;

import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final ContratRepository contratRepository;

    @Override
    public Reservation ajouterReservation(Reservation reservation) {
        validerRelations(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation modifierReservation(Reservation reservation) {
        validerRelations(reservation);
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation getReservation(long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerReservationById(Long id) {
        if (contratRepository.existsByReservation_IdReservation(id)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Impossible de supprimer la réservation " + id + " : un contrat est encore rattaché."
            );
        }
        reservationRepository.deleteById(id);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    private void validerRelations(Reservation reservation) {
        if (reservation.getVehicule() == null
            || reservation.getVehicule().getIdVehicule() == null
            || reservation.getVehicule().getIdVehicule() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le champ vehicule.idVehicule est obligatoire et doit être un id existant (jamais 0)."
            );
        }
        if (reservation.getClient() == null
            || reservation.getClient().getIdClient() == null
            || reservation.getClient().getIdClient() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le champ client.idClient est obligatoire et doit être un id existant (jamais 0)."
            );
        }

        Long idVehicule = reservation.getVehicule().getIdVehicule();
        Vehicule vehicule = vehiculeRepository.findById(idVehicule)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Vehicule introuvable avec idVehicule = " + idVehicule
            ));

        Long idClient = reservation.getClient().getIdClient();
        Client client = clientRepository.findById(idClient)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Client introuvable avec idClient = " + idClient
            ));

        reservation.setVehicule(vehicule);
        reservation.setClient(client);
    }
}
