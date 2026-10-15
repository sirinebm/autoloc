package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Equipement;
import tn.esprit.autoloc.service.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/equipements")
@AllArgsConstructor
public class EquipementController {

    private final IEquipementService equipementService;

    @PostMapping
    public Equipement ajouterEquipement(@RequestBody Equipement equipement) {
        return equipementService.ajouterEquipement(equipement);
    }

    @PutMapping
    public Equipement modifierEquipement(@RequestBody Equipement equipement) {
        return equipementService.modifierEquipement(equipement);
    }

    @GetMapping("/{id}")
    public Equipement getEquipement(@PathVariable long id) {
        return equipementService.getEquipement(id);
    }

    @DeleteMapping("/{id}")
    public void supprimerEquipementById(@PathVariable Long id) {
        equipementService.supprimerEquipementById(id);
    }

    @GetMapping
    public List<Equipement> getAllEquipements() {
        return equipementService.getAllEquipements();
    }
}
