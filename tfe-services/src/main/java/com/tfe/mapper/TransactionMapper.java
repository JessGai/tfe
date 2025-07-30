package com.tfe.mapper;

import com.tfe.dto.TransactionDTO;
import com.tfe.entity.ParentEntity;
import com.tfe.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "idParent", source = "parent.idParent")
    TransactionDTO toDto(TransactionEntity entity);
    @Mapping(target = "parent", expression = "java(toParentEntity(dto.getIdParent()))")
    TransactionEntity toEntity (TransactionDTO dto);
    default ParentEntity toParentEntity(int idParent) {
        if (idParent == 0) {
            return null;
        }
        ParentEntity parent = new ParentEntity();
        parent.setIdParent(idParent);
        return parent;
    }
    List<TransactionDTO> toDtoList(List<TransactionEntity> entities);
    List<TransactionEntity> toEntityList(List<TransactionDTO> dtos);
}
