package tn.esprit.autoloc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.service.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/vehicules")
@Tag(name = "Gestion des Vehicules", description = "CRUD des véhicules AutoLoc")
@AllArgsConstructor
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    @PostMapping
    @Operation(summary = "Ajouter un vehicule")
    public ResponseEntity<Vehicule> ajouterVehicule(@RequestBody Vehicule vehicule) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculeService.ajouterVehicule(vehicule));
    }

    @GetMapping
    @Operation(summary = "Récupérer tous les vehicules")
    public ResponseEntity<List<Vehicule>> getAllVehicules() {
        return ResponseEntity.ok(vehiculeService.getAllVehicules());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un vehicule par son id")
    public ResponseEntity<Vehicule> getVehicule(@PathVariable Long id) {
        Vehicule vehicule = vehiculeService.getVehicule(id);
        if (vehicule == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehicule);
    }

    @PutMapping
    @Operation(summary = "Modifier un vehicule")
    public ResponseEntity<Vehicule> modifierVehicule(@RequestBody Vehicule vehicule) {
        if (vehicule.getIdVehicule() == null || vehiculeService.getVehicule(vehicule.getIdVehicule()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculeService.modifierVehicule(vehicule));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un vehicule")
    public ResponseEntity<Void> supprimerVehiculeById(@PathVariable Long id) {
        if (vehiculeService.getVehicule(id) == null) {
            return ResponseEntity.notFound().build();
        }
        vehiculeService.supprimerVehiculeById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idVehicule}/equipements/{idEquipement}/affecter")
    @Operation(summary = "Affecter un équipement à un véhicule")
    public ResponseEntity<Vehicule> affecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return ResponseEntity.ok(vehiculeService.affecterEquipement(idVehicule, idEquipement));
    }

    @PutMapping("/{idVehicule}/equipements/{idEquipement}/desaffecter")
    @Operation(summary = "Désaffecter un équipement d’un véhicule")
    public ResponseEntity<Vehicule> desaffecterEquipement(@PathVariable Long idVehicule, @PathVariable Long idEquipement) {
        return ResponseEntity.ok(vehiculeService.desaffecterEquipement(idVehicule, idEquipement));
    }
}
