package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.service.IReservationService;

@Service
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
