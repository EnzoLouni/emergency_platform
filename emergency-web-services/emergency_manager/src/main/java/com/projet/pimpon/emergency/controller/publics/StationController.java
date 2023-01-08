package com.projet.pimpon.emergency.controller.publics;

import com.fasterxml.jackson.annotation.JsonView;
import com.projet.pimpon.emergency.controller.views.Views;
import com.projet.pimpon.emergency.dtos.dtoapi.StationDtoApi;
import com.projet.pimpon.emergency.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/station")
@RequiredArgsConstructor
public class StationController {

    private final StationService stationService;

    @JsonView({Views.ContextStationView.class})
    @GetMapping
    public List<StationDtoApi> getStations() {
        return stationService.getAllStations();
    }
}
