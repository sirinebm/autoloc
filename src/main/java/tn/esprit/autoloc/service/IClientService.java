package tn.esprit.autoloc.service;

import tn.esprit.autoloc.domain.Client;

import java.util.List;

public interface IClientService {
    Client ajouterClient(Client client);
    Client modifierClient(Client client);
    Client getClient(long id);
    void supprimerClientById(Long id);
    List<Client> getAllClients();
}
