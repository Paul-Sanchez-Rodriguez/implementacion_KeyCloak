package com.soa.canete.Transaccional_Act_Teen.domain.mapper;

import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationRequestDto;
import com.soa.canete.Transaccional_Act_Teen.domain.dto.Transaccional.TransaccionalAllocationResponseDto;
import com.soa.canete.Transaccional_Act_Teen.domain.model.TransaccionalActTeen;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TransaccionalActTeenMapper {

    public static TransaccionalActTeen toModel(TransaccionalAllocationRequestDto dto) {
        return new TransaccionalActTeen(
                dto.getId_activities(),
                dto.getId_teenager(),
                dto.getStart_date(),
                dto.getDuration(),
                dto.getNotes(),
                dto.getParticipation_status(),
                dto.getActive()
        );
    }

    public static TransaccionalActTeen toModel(TransaccionalAllocationRequestDto dto, Integer id_act_teen) {
        return new TransaccionalActTeen(
                id_act_teen,
                dto.getId_activities(),
                dto.getId_teenager(),
                dto.getStart_date(),
                dto.getDuration(),
                dto.getNotes(),
                dto.getParticipation_status(),
                dto.getActive()
        );
    }

    public static TransaccionalAllocationResponseDto toDto(TransaccionalActTeen model) {
        return new TransaccionalAllocationResponseDto(
                model.getId_act_teen(),
                model.getId_activities(),
                model.getId_teenager(),
                model.getStart_date(),
                model.getDuration(),
                model.getNotes(),
                model.getParticipation_status(),
                model.getActive()
        );
    }

}
