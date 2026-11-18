package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@Tag(name = "Gestion des Agences", description = "CRUD des agences AutoLoc")
@AllArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    @Operation(summary = "Ajouter une agence")
    public ResponseEntity<Agence> ajouterAgence(@RequestBody Agence agence) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agenceService.ajouterAgence(agence));
    }

    @GetMapping
    @Operation(summary = "Récupérer toutes les agences")
    public ResponseEntity<List<Agence>> getAllAgences() {
        return ResponseEntity.ok(agenceService.getAllAgences());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une agence par son id")
    public ResponseEntity<Agence> getAgence(@PathVariable Long id) {
        Agence agence = agenceService.getAgence(id);
        if (agence == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agence);
    }

    @PutMapping
    @Operation(summary = "Modifier une agence")
    public ResponseEntity<Agence> modifierAgence(@RequestBody Agence agence) {
        if (agence.getIdAgence() == null || agenceService.getAgence(agence.getIdAgence()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(agenceService.modifierAgence(agence));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer une agence")
    public ResponseEntity<Void> supprimerAgenceById(@PathVariable Long id) {
        if (agenceService.getAgence(id) == null) {
            return ResponseEntity.notFound().build();
        }
        agenceService.supprimerAgenceById(id);
        return ResponseEntity.noContent().build();
    }
}
