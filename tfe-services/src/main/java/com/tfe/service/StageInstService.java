package com.tfe.service;

import com.tfe.StageDescRepository;
import com.tfe.StageInstanceRepository;
import com.tfe.dto.StageForCardsDTO;
import com.tfe.dto.StageInstDto;
import com.tfe.entity.StageDescEntity;
import com.tfe.entity.StageInstanceEntity;
import com.tfe.exception.StageNotFoundException;
import com.tfe.mapper.StageInstMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StageInstService {
    private final StageInstanceRepository repository;
    private final StageInstMapper mapper;
    private final StageDescRepository stageDescRepository;
    public StageInstService(StageInstanceRepository repository, StageInstMapper mapper, StageDescRepository stageDescRepository){
        this.repository=repository;
        this.mapper = mapper;
        this.stageDescRepository = stageDescRepository;
    }

    public List<StageInstDto> getAllStageInst() {
        List<StageInstanceEntity> lst = repository.findAll();
        return mapper.toDtoList(lst);
    }
    public StageInstDto getStageInstById(int id){
        StageInstanceEntity entity = repository.findById(id)
                .orElseThrow(()-> new StageNotFoundException(id));
        return mapper.toDto(entity);
    }

    public StageInstDto saveStageInst(StageInstDto dto){
        StageInstanceEntity entity = mapper.toEntity(dto);
        StageInstanceEntity savedEntity = repository.save(entity);
        return mapper.toDto(savedEntity);
    }
    public StageInstDto updageStage(int id, StageInstDto dto){
        //trouver le stage a modifier
        StageInstanceEntity entity = repository.findById(id)
                .orElseThrow(()-> new StageNotFoundException(id));
        //trouve l'entity du stagedesc
        StageDescEntity stageDescEntity = stageDescRepository.findById(dto.getIdStageDesc())
                .orElseThrow(()->new StageNotFoundException(id));
        //mise a jour des données
        entity.setPrix(dto.getPrix());
        entity.setDateDebut(dto.getDateDebut());
        entity.setNbrParticipant(dto.getNbrParticipant());
        entity.setNbrInscrit(dto.getNbrInscrit());
        entity.setStatut(dto.getStatut());
        entity.setStageDesc(stageDescEntity);

        //sauvegarde dans la db:
        StageInstanceEntity updated = repository.save(entity);
        return mapper.toDto(updated);
    }

    public void deleteStageInstBtId(int id){
        if(!repository.existsById(id)){
            throw new StageNotFoundException(id);
        }
        repository.deleteById(id);
    }
    public List<StageInstDto> getStageInstByTheme(String theme){
        List<StageInstanceEntity> lstEntity = repository.findByTheme(theme);
        return mapper.toDtoList(lstEntity);
    }

    public List<StageForCardsDTO> getAllStageForCards(){
        return repository.findAllVisibleCards();
    }
}
