package com.tfe.service;

import com.tfe.ParentRepository;
import com.tfe.dto.ParentDTO;
import com.tfe.entity.ParentEntity;
import com.tfe.exception.ParentAlreadyExistsException;
import com.tfe.exception.ParentAuth0NotFound;
import com.tfe.exception.ParentNotFoundException;
import com.tfe.mapper.ParentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentService {
    private static final Logger logger = LoggerFactory.getLogger(ParentService.class);
    private final ParentRepository repository;
    private final ParentMapper mapper;

    public ParentService(ParentRepository repository, ParentMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public ParentDTO getParentById(int id){
        ParentEntity entity = repository.findById(id)
                .orElseThrow(() -> {
                    logger.info("Parent not found with id : {}", id);
                    return new ParentNotFoundException(id);
                });
        logger.info("Parent found with id : {}", id);
        return mapper.toDto(entity);
    }

    public List<ParentDTO> getAllParents(){
        List<ParentEntity> lst = repository.findAll();
        return mapper.toDtoList(lst);
    }
    public ParentDTO getParentByAuth0UserId(String auth0UserId) {
        ParentEntity entity = repository.findByAuth0UserId(auth0UserId)
                .orElseThrow(() -> new ParentAuth0NotFound(auth0UserId));
        return mapper.toDto(entity);
    }

    public ParentDTO createParent(ParentDTO dto, String auth0UserId) {
        if (repository.existsByAuth0UserId(auth0UserId)) {
            logger.warn("Parent already exists with auth0UserId : {}", auth0UserId);
            throw new ParentAlreadyExistsException(auth0UserId);
        }
        dto.setAuth0UserId(auth0UserId);
        ParentEntity entity = mapper.toEntity(dto);
        ParentEntity saved = repository.save(entity);
        logger.info("Parent created with id : {}", saved.getIdParent());
        return mapper.toDto(saved);
    }

    public ParentDTO updateParent(int id, ParentDTO dto) {
        ParentEntity existing = repository.findById(id)
                .orElseThrow(() -> new ParentNotFoundException(id));

        existing.setEmail(dto.getEmail());
        existing.setAdresse(dto.getAdresse());
        existing.setCodePostal(dto.getCodePostal());
        existing.setCommune(dto.getCommune());
        existing.setTelephone1(dto.getTelephone1());
        existing.setTelephone2(dto.getTelephone2());

        ParentEntity updated = repository.save(existing);
        logger.info("Parent updated with id : {}", id);
        return mapper.toDto(updated);
    }
    public void deleteParentById(int id) {
        if(!repository.existsById(id)){
            throw new ParentNotFoundException(id);
        }
        repository.deleteById(id);
        logger.info("Parent deleted with id : {}", id);
    }
}
