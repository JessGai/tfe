package com.tfe.mapper;

import com.tfe.dto.StageInstDto;
import com.tfe.entity.StageDescEntity;
import com.tfe.entity.StageInstanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StageInstMapper {

    @Mapping(source = "stageDesc.idStageDesc", target = "idStageDesc")
    StageInstDto toDto(StageInstanceEntity entity);

    // Pour convertir un DTO en Entity, on crée un StageDescEntity à partir de l'id
    @Mapping(target = "stageDesc", expression = "java(createStageDescEntity(dto.getIdStageDesc()))")
    StageInstanceEntity toEntity(StageInstDto dto);

    List<StageInstDto> toDtoList(List<StageInstanceEntity> entities);

    default StageDescEntity createStageDescEntity(int idStageDesc) {
        if (idStageDesc == 0) {
            return null;
        }
        StageDescEntity stageDesc = new StageDescEntity();
        stageDesc.setIdStageDesc(idStageDesc);
        return stageDesc;
    }
}
