package com.gestion.activities.domain.mapper;

import com.gestion.activities.domain.dto.ActivitiesRequestDto;
import com.gestion.activities.domain.dto.ActivitiesResponseDto;
import com.gestion.activities.domain.model.Activities;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ActivitiesMapper {

    public static Activities toModel(ActivitiesRequestDto dto) {
        return new Activities(
                dto.getName(),
                dto.getDescription(),
                dto.getDate(),
                dto.getDuration(),
                dto.getLocation(),
                dto.getActive(),
                dto.getType_pronacej(),
                dto.getType_soa()
        );
    }

    public static Activities toModel(ActivitiesRequestDto dto, Integer id) {
        return new Activities(
                id,
                dto.getName(),
                dto.getDescription(),
                dto.getDate(),
                dto.getDuration(),
                dto.getLocation(),
                dto.getActive(),
                dto.getType_pronacej(),
                dto.getType_soa()
        );
    }

    public static ActivitiesResponseDto toDto(Activities model) {
        return new ActivitiesResponseDto(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getDate(),
                model.getDuration(),
                model.getLocation(),
                model.getActive(),
                model.getType_pronacej(),
                model.getType_soa()
        );
    }

}

