package com.tfe.mapper;

import com.tfe.dto.StageDescDTO;
import com.tfe.entity.StageDescEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StageDescMapper {
    StageDescMapper INSTANCE = Mappers.getMapper(StageDescMapper.class);

    StageDescDTO toDto(StageDescEntity entity);

    StageDescEntity toEntity(StageDescDTO dto);
    List<StageDescDTO> toDtoList(List<StageDescEntity> entities);

    List<StageDescEntity> toEntityList(List<StageDescDTO> dtos);


}
