package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Client;
import tn.esprit.autoloc.service.IClientService;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Tag(name = "Gestion des Clients", description = "CRUD des clients AutoLoc")
@AllArgsConstructor
public class ClientController {

    private final IClientService clientService;

    @PostMapping
    @Operation(summary = "Ajouter un client")
    public ResponseEntity<Client> ajouterClient(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.ajouterClient(client));
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les clients")
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un client par son id")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Client client = clientService.getClient(id);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PutMapping
    @Operation(summary = "Modifier un client")
    public ResponseEntity<Client> modifierClient(@RequestBody Client client) {
        if (client.getIdClient() == null || clientService.getClient(client.getIdClient()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientService.modifierClient(client));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un client")
    public ResponseEntity<Void> supprimerClientById(@PathVariable Long id) {
        if (clientService.getClient(id) == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.supprimerClientById(id);
        return ResponseEntity.noContent().build();
    }
}
