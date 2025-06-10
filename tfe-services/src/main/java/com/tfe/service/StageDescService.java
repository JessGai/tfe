package com.tfe.service;

import com.tfe.StageDescRepository;
import com.tfe.StageInstanceRepository;
import com.tfe.dto.StageDescDTO;
import com.tfe.dto.StageDescWithInstancesDTO;
import com.tfe.dto.StageInstDto;
import com.tfe.entity.StageDescEntity;
import com.tfe.entity.StageInstanceEntity;
import com.tfe.exception.StageNotFoundException;
import com.tfe.mapper.StageDescMapper;
import com.tfe.mapper.StageInstMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StageDescService {
    private final StageDescRepository repository;
    private final StageDescMapper mapper;
    private final StageInstanceRepository stageInstanceRepository;
    private final StageInstMapper stageInstMapper;
    public StageDescService(StageDescRepository repository, StageDescMapper mapper, StageInstanceRepository stageInstanceRepository,
                            StageInstMapper stageInstMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.stageInstanceRepository = stageInstanceRepository;
        this.stageInstMapper = stageInstMapper;
    }

    public StageDescDTO getStageDescById(int id) {
        StageDescEntity entity = repository.findById(id)
                .orElseThrow(() -> new StageNotFoundException(id));
        return mapper.toDto(entity);
    }

    public Page<StageDescDTO> getAllStageDesc(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<StageDescEntity> pageResult = repository.findAll(pageable);


        return pageResult.map(mapper::toDto);
    }

    public StageDescDTO saveStageDesc(StageDescDTO dto) {
        StageDescEntity entity = mapper.toEntity(dto);
        StageDescEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }
    // methode pour avoir stage description et ses instances
    public List<StageDescWithInstancesDTO> getStageDescWithInstances() {
        // Récupérer toutes les descriptions de stage
        List<StageDescEntity> stageDescriptions = repository.findAll();

        List<StageDescWithInstancesDTO> result = new ArrayList<>();

        for (StageDescEntity stageDesc : stageDescriptions) {
            // Créer le DTO combiné à partir de l'entity
            StageDescWithInstancesDTO dto = new StageDescWithInstancesDTO(stageDesc);

            // Récupérer les instances pour cette description
            List<StageInstanceEntity> instances = stageInstanceRepository.findByStageDesc_IdStageDesc(stageDesc.getIdStageDesc());

            // Convertir les instances en DTO et les ajouter
            List<StageInstDto> instanceDTOs = stageInstMapper.toDtoList(instances);
            dto.setInstances(instanceDTOs);

            result.add(dto);
        }

        return result;
    }
}
