package com.tfe.mapper;

import com.tfe.dto.InscriptionDTO;
import com.tfe.dto.PanierDTO;
import com.tfe.entity.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PanierMapper {
    PanierDTO toDto(PanierEntity entity);
    default PanierEntity toEntity(PanierDTO dto){
        PanierEntity entity = new PanierEntity();

        entity.setIdPanier(dto.getIdPanier());

        // ATTENTION : il faut injecter les services ou repositories pour trouver les entités => voir explication ci-dessous
        ParentEntity parent = new ParentEntity();
        parent.setIdParent(dto.getIdParent());
        entity.setParent(parent);

        EnfantEntity enfant = new EnfantEntity();
        enfant.setIdEnfant(dto.getIdEnfant());
        entity.setEnfant(enfant);

        StageInstanceEntity stageInstance = new StageInstanceEntity();
        stageInstance.setIdStageInst(dto.getIdStageInstance());
        entity.setStageInstance(stageInstance);

        return entity;
    }

    List<PanierDTO> toDtoList(List<PanierEntity> entities);
    List<PanierEntity> toEntityList(List<PanierDTO> dtos);
}
