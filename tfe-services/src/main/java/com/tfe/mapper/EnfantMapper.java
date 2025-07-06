package com.tfe.mapper;

import com.tfe.dto.EnfantDTO;
import com.tfe.entity.EnfantEntity;
import com.tfe.entity.ParentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnfantMapper {
//l'enfant depend du parent d'ou la source vers le parent
    @Mapping(source = "parent.idParent", target = "idParent")
    EnfantDTO toDto(EnfantEntity entity);

    @Mapping(target = "parent", expression = "java(createParentEntity(dto.getIdParent()))")
    EnfantEntity toEntity(EnfantDTO dto);

    List<EnfantDTO> toDtoList(List<EnfantEntity> entities);

    List<EnfantEntity> toEntityList(List<EnfantDTO> dtos);

    // Méthode utilitaire appelée dans le mapping ci-dessus
    default ParentEntity createParentEntity(int idParent) {
        if (idParent == 0) {
            return null;
        }
        ParentEntity parent = new ParentEntity();
        parent.setIdParent(idParent);
        return parent;
    }
}
