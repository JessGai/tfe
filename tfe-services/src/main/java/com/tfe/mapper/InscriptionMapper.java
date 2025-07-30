package com.tfe.mapper;

import com.tfe.dto.InscriptionDTO;
import com.tfe.entity.InscriptionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscriptionMapper{
    InscriptionDTO toDto(InscriptionEntity entity);
    InscriptionEntity toEntity(InscriptionDTO dto);
    List<InscriptionDTO> toDtoList(List<InscriptionEntity> entities);
    List<InscriptionEntity> toEntityList(List<InscriptionDTO> dtos);
}
