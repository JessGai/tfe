package com.tfe.service;

import com.tfe.EnfantRepository;
import com.tfe.ParentRepository;
import com.tfe.dto.EnfantDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import com.tfe.exception.ChildNotFoundException;
import com.tfe.mapper.EnfantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EnfantService {
    private static final Logger logger = LoggerFactory.getLogger(ParentService.class);
    private final EnfantRepository repository;
    private final EnfantMapper mapper;
    private final ParentRepository parentRepository;

    public EnfantService(EnfantRepository repository, EnfantMapper mapper, ParentRepository parentRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.parentRepository = parentRepository;
    }

    public EnfantDTO getEnfantById(int id){
        EnfantEntity entity = repository.findById(id)
                .orElseThrow(() -> {
                    logger.info("Child not found with id : {}", id);
                    return new ChildNotFoundException(id);
                });
        logger.info("Child found with id : {}", id);
        return mapper.toDto(entity);
    }

    public EnfantDTO createChild(EnfantDTO dto){
        EnfantEntity entity = mapper.toEntity(dto);
        EnfantEntity savedChild = repository.save(entity);
        return mapper.toDto(savedChild);
    }

    public EnfantDTO updateChild(int id, EnfantDTO dto){
        // Récupérer l'enfant en base
        EnfantEntity enfantEntity = repository.findById(id)
                .orElseThrow(() -> new ChildNotFoundException(id));

        // Optionnel : vérifier que le parent existe si tu modifies le parent (sinon ignore)
        if (dto.getIdParent() != 0 && dto.getIdParent() != enfantEntity.getParent().getIdParent()) {
            ParentEntity parent = parentRepository.findById(dto.getIdParent())
                    .orElseThrow(() -> new RuntimeException("Parent non trouvé avec l'id: " + dto.getIdParent()));
            enfantEntity.setParent(parent);
        }

        enfantEntity.setNomEnfant(dto.getNomEnfant());
        enfantEntity.setPrenomEnfant(dto.getPrenomEnfant());
        enfantEntity.setDateNaissance(dto.getDateNaissance());
        enfantEntity.setGenre(dto.getGenre());
        enfantEntity.setLangueMaternelle(dto.getLangueMaternelle());

        EnfantEntity updatedEnfant = repository.save(enfantEntity);

        return mapper.toDto(updatedEnfant);
    }

    public void deleteChildById (int id){
        if(!repository.existsById(id)){
            throw new ChildNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
