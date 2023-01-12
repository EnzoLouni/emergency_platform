package com.projet.pimpon.emergency.dtos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.postgresql.geometric.PGpoint;

@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDto implements Comparable<SensorDto>{
    private Integer id;
    private PGpoint coordinates;
    private Boolean status;

    @Override
    public int compareTo(@NotNull SensorDto o) {
        if(coordinates.equals(o.coordinates))
            return 0;
        else if(Double.compare(coordinates.y, o.coordinates.y) == 0)
            return Double.compare(o.coordinates.x,coordinates.x);
        else
            return Double.compare(coordinates.y, o.coordinates.y);
    }
}
