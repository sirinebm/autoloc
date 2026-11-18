package tn.esprit.autoloc.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.ContratRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@Service
@AllArgsConstructor
public class ContratServiceImpl implements IContratService {

    private final ContratRepository contratRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Contrat ajouterContrat(Contrat contrat) {
        Reservation reservation = recupererReservationExistante(contrat);

        if (contratRepository.existsByReservation_IdReservation(reservation.getIdReservation())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Cette réservation possède déjà un contrat (idReservation = "
                    + reservation.getIdReservation() + ")."
            );
        }

        contrat.setReservation(reservation);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat modifierContrat(Contrat contrat) {
        Reservation reservation = recupererReservationExistante(contrat);
        contrat.setReservation(reservation);
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat getContrat(long id) {
        return contratRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerContratById(Long id) {
        contratRepository.deleteById(id);
    }

    @Override
    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    private Reservation recupererReservationExistante(Contrat contrat) {
        if (contrat.getReservation() == null
            || contrat.getReservation().getIdReservation() == null
            || contrat.getReservation().getIdReservation() <= 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Le champ reservation.idReservation est obligatoire et doit être un id existant (jamais 0)."
            );
        }

        Long idReservation = contrat.getReservation().getIdReservation();
        return reservationRepository.findById(idReservation)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Réservation introuvable avec idReservation = " + idReservation
            ));
    }
}
