package tn.esprit.autoloc.service.impl;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.repository.AgenceRepository;
import tn.esprit.autoloc.service.IAgenceService;

@Service
public class AgenceServiceImpl implements IAgenceService {

    private final AgenceRepository agenceRepository;

    public AgenceServiceImpl(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }
}
