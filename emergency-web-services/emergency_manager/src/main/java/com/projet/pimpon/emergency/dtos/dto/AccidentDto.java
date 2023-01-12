package com.projet.pimpon.emergency.dtos.dto;

import com.projet.pimpon.emergency.model.AccidentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.postgresql.geometric.PGpoint;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccidentDto {
    private Integer id;
    private Integer intensity;
    private Integer teamId;
    private AccidentStatus status;
    private PGpoint coordinates;
}
