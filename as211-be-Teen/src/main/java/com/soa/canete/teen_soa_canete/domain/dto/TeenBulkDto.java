package com.soa.canete.teen_soa_canete.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TeenBulkDto {
    private Integer legalGuardianId;
    private String description;
    private List<TeenAssignDto> teens;

    @Getter
    @Setter
    public static class TeenAssignDto {
        private Integer teenId;
    }
}
