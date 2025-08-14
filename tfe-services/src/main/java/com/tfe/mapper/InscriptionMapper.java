package com.tfe.mapper;

import com.tfe.dto.InscriptionDTO;
import com.tfe.entity.InscriptionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscriptionMapper{

    @Mapping(source = "enfant.idEnfant", target = "idEnfant")
    @Mapping(source = "stageInstance.idStageInst", target = "idStageInstance")
    InscriptionDTO toDto(InscriptionEntity entity);
    @Mapping(target = "enfant.idEnfant", source = "idEnfant")
    @Mapping(target = "stageInstance.idStageInst", source = "idStageInstance")
    InscriptionEntity toEntity(InscriptionDTO dto);
    List<InscriptionDTO> toDtoList(List<InscriptionEntity> entities);
    List<InscriptionEntity> toEntityList(List<InscriptionDTO> dtos);
}
