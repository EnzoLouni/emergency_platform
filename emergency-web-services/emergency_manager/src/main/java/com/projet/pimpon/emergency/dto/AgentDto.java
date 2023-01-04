package com.projet.pimpon.emergency.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgentDto {
    private String fullName;
    private Integer tiredness;
    private Integer quality;
}
