package tn.esprit.autoloc.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.repository.ClientRepository;
import tn.esprit.autoloc.repository.ReservationRepository;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {

    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public Client ajouterClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client modifierClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClient(long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public void supprimerClientById(Long id) {
        if (reservationRepository.existsByClient_IdClient(id)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Impossible de supprimer le client " + id + " : des réservations sont encore rattachées."
            );
        }
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
