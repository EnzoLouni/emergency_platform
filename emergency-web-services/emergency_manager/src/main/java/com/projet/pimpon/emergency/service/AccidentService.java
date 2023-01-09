package com.projet.pimpon.emergency.service;

import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.turf.TurfMeasurement;
import com.mapbox.turf.TurfTransformation;
import com.projet.pimpon.emergency.controller.gateway.MqttGateway;
import com.projet.pimpon.emergency.dao.AccidentRepository;
import com.projet.pimpon.emergency.dtos.dto.AccidentDto;
import com.projet.pimpon.emergency.dtos.dto.SensorDto;
import com.projet.pimpon.emergency.mapper.AccidentMapper;
import com.projet.pimpon.emergency.model.AccidentStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;

@Validated
@Slf4j
@Service
@RequiredArgsConstructor
public class AccidentService {

    private final AccidentRepository accidentRepository;

    private final AccidentMapper accidentMapper;

    private final SensorService sensorService;

    private final MqttGateway mqttGateway;

    private static Integer LEVELS = 10;
    private static String SIMULATOR_EMERGENCY = "simulatorEmergency";

    public void registerAccident(String sensors) {
        List<SensorDto> sensorsTriggered = sensorService.getSensorsTriggered(sensors);
        log.debug(sensorsTriggered.toString());
        String[] sensorsData = sensors.split("[)],");
        Integer intensity = 0;
        for (String sensorData: sensorsData) {
            Integer intensityDetected = Integer.parseInt(sensorData.substring(sensorData.lastIndexOf(',')+1, sensorData.lastIndexOf(')') != -1 ? sensorData.lastIndexOf(')') : sensorData.length()));
            if(intensity < intensityDetected)
                intensity = intensityDetected;
        }
        Point point1 = Point.fromLngLat(sensorsTriggered.get(0).getCoordinates().x, sensorsTriggered.get(0).getCoordinates().y);
        Point point2 = Point.fromLngLat(sensorsTriggered.get(1).getCoordinates().x, sensorsTriggered.get(1).getCoordinates().y);
        Double distanceMeasure = TurfMeasurement.distance(point1, point2);
        List<Double> distanceLevels = new ArrayList<>();
        for(Integer i = 0; i < LEVELS; ++i) {
            distanceLevels.add(distanceMeasure/10*i);
        }
        List<Polygon> circles = new ArrayList<>();
        for(SensorDto sensor: sensorsTriggered) {
            Point point = Point.fromLngLat(sensor.getCoordinates().x, sensor.getCoordinates().y);
            circles.add(TurfTransformation.circle(point, distanceMeasure));
        }
        AccidentDto accidentDto = new AccidentDto(null, intensity, AccidentStatus.NOT_TREATED, null);
        //mqttGateway.sendToMqtt(SIMULATOR_EMERGENCY, "hello_world");
        //accidentRepository.save(accidentMapper.toAccident(accidentDto));
    }
}
