package com.tfe.mapper;

import com.tfe.dto.ParentDTO;
import com.tfe.entity.ParentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParentMapper {
    ParentDTO toDto(ParentEntity entity);

    ParentEntity toEntity(ParentDTO dto);

    List<ParentDTO> toDtoList(List<ParentEntity> entities);

    List<ParentEntity> toEntityList(List<ParentDTO> dtos);
}
