package com.projet.pimpon.emergency.utils;

import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import com.projet.pimpon.emergency.dtos.dto.Square;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.geometric.PGpoint;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class SquareUtils {
    public static List<Square> toSquareList(List<List<SensorDto>> multiArraySensors, List<Integer> intensitiesDetected) {
        List<Square> squares = new ArrayList<>();
        Integer latitude = multiArraySensors.size();
        Integer longitude = multiArraySensors.get(0).size();
        for(Integer x = 0; x < latitude - 1; ++x) {
            List<SensorDto> line1 = multiArraySensors.get(x);
            List<SensorDto> line2 = multiArraySensors.get(x+1);
            for(Integer y = 0; y < longitude - 1; ++y) {
                PGpoint p1 = line1.get(y).getCoordinates();
                PGpoint p4 = line2.get(y+1).getCoordinates();
                PGpoint center = new PGpoint(p4.x-(p4.x-p1.x)/2, p4.y-(p4.y-p1.y)/2);
                Integer intensityOfSquare = 0;
                if(!intensitiesDetected.get((longitude)*x + y).equals(0) && !intensitiesDetected.get((longitude)*x + y + 1).equals(0) && !intensitiesDetected.get((longitude)*(x+1) + y).equals(0) &&
                        !intensitiesDetected.get((longitude)*(x+1) + y + 1).equals(0)) {
                    intensityOfSquare = (int)(Math.floor(intensitiesDetected.get(longitude*x + y) + intensitiesDetected.get(longitude*x + y + 1 + intensitiesDetected.get(longitude*(x+1) + y) +
                            intensitiesDetected.get(longitude*(x+1) + y + 1)) / 4));
                }
                squares.add(new Square(center, intensityOfSquare));
            }
        }
        return squares;
    }
}
