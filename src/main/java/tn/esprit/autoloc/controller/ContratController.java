package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Contrat;
import tn.esprit.autoloc.service.IContratService;

import java.util.List;

@RestController
@RequestMapping("/contrats")
@Tag(name = "Gestion des Contrats", description = "CRUD des contrats AutoLoc")
@AllArgsConstructor
public class ContratController {

    private final IContratService contratService;

    @PostMapping
    @Operation(summary = "Ajouter un contrat")
    public ResponseEntity<Contrat> ajouterContrat(@RequestBody Contrat contrat) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contratService.ajouterContrat(contrat));
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les contrats")
    public ResponseEntity<List<Contrat>> getAllContrats() {
        return ResponseEntity.ok(contratService.getAllContrats());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un contrat par son id")
    public ResponseEntity<Contrat> getContrat(@PathVariable Long id) {
        Contrat contrat = contratService.getContrat(id);
        if (contrat == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contrat);
    }

    @PutMapping
    @Operation(summary = "Modifier un contrat")
    public ResponseEntity<Contrat> modifierContrat(@RequestBody Contrat contrat) {
        if (contrat.getIdContrat() == null || contratService.getContrat(contrat.getIdContrat()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(contratService.modifierContrat(contrat));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un contrat")
    public ResponseEntity<Void> supprimerContratById(@PathVariable Long id) {
        if (contratService.getContrat(id) == null) {
            return ResponseEntity.notFound().build();
        }
        contratService.supprimerContratById(id);
        return ResponseEntity.noContent().build();
    }
}
