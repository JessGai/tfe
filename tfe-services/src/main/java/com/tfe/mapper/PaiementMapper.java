package com.tfe.mapper;

import com.tfe.dto.PaiementDTO;
import com.tfe.entity.PaiementEntity;
import com.tfe.entity.ParentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaiementMapper {
    @Mapping(source = "parent.idParent", target = "idParent")
    PaiementDTO toDto(PaiementEntity entity);

    @Mapping(target = "parent", source = "idParent", qualifiedByName = "idToParent")
    PaiementEntity toEntity(PaiementDTO dto);

    List<PaiementDTO> toDtoList(List<PaiementEntity> entities);

    List<PaiementEntity> toEntityList(List<PaiementDTO> dtos);

    @Named("idToParent")
    default ParentEntity idToParent(int idParent) {
        ParentEntity parent = new ParentEntity();
        parent.setIdParent(idParent);
        return parent;
    }
}
