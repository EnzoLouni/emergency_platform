package com.projet.pimpon.emergency.utils;

import com.projet.pimpon.emergency.model.Coordinates;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class Calculation {
    public static Double distance(Coordinates coord1, Coordinates coord2) {
        return Math.sqrt((coord1.getLongitude() - coord2.getLongitude()) *  (coord1.getLongitude() - coord2.getLongitude()) + (coord1.getLatitude() - coord2.getLatitude()) * (coord1.getLatitude() - coord2.getLatitude()));
    }
}
