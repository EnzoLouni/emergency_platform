package com.projet.pimpon.emergency.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Coordinates {
    private Double longitude;
    private Double latitude;
}
