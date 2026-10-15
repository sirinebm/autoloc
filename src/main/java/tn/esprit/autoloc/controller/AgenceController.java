package tn.esprit.autoloc.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.service.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/agences")
@AllArgsConstructor
public class AgenceController {

    private final IAgenceService agenceService;

    @PostMapping
    public Agence ajouterAgence(@RequestBody Agence agence) {
        return agenceService.ajouterAgence(agence);
    }

    @PutMapping
    public Agence modifierAgence(@RequestBody Agence agence) {
        return agenceService.modifierAgence(agence);
    }

    @GetMapping("/{id}")
    public Agence getAgence(@PathVariable long id) {
        return agenceService.getAgence(id);
    }

    @DeleteMapping("/{id}")
    public void supprimerAgenceById(@PathVariable Long id) {
        agenceService.supprimerAgenceById(id);
    }

    @GetMapping
    public List<Agence> getAllAgences() {
        return agenceService.getAllAgences();
    }
}
