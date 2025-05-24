package com.tfe.service;

import com.tfe.StageDescRepository;
import com.tfe.dto.StageDescDTO;
import com.tfe.entity.StageDescEntity;
import com.tfe.exception.StageNotFoundException;
import com.tfe.exception.EmptyStageListException;
import com.tfe.mapper.StageDescMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StageDescService {
    private final StageDescRepository repository;
    private final StageDescMapper mapper;

    public StageDescService(StageDescRepository repository, StageDescMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
}
