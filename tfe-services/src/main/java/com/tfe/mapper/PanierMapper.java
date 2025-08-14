package com.tfe.mapper;

import com.tfe.dto.InscriptionDTO;
import com.tfe.dto.PanierDTO;
import com.tfe.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PanierMapper {

    @Mapping(source = "parent.idParent", target = "idParent")
    @Mapping(source = "enfant.idEnfant", target = "idEnfant")
    @Mapping(source = "stageInstance.idStageInst", target = "idStageInstance")
    PanierDTO toDto(PanierEntity entity);


    @Mapping(target = "parent.idParent", source = "idParent")
    @Mapping(target = "enfant.idEnfant", source = "idEnfant")
    @Mapping(target = "stageInstance.idStageInst", source = "idStageInstance")
    PanierEntity toEntity(PanierDTO dto);

    List<PanierDTO> toDtoList(List<PanierEntity> entities);

    List<PanierEntity> toEntityList(List<PanierDTO> dtos);
}
