package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Reservation;

import java.util.List;

public interface IReservationService {
    Reservation ajouterReservation(Reservation reservation);
    Reservation modifierReservation(Reservation reservation);
    Reservation getReservation(long id);
    void supprimerReservationById(Long id);
    List<Reservation> getAllReservations();
}
