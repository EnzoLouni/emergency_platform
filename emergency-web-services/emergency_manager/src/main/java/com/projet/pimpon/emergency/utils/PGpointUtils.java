package com.projet.pimpon.emergency.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.geometric.PGpoint;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class PGpointUtils {
    public static Double distance(PGpoint p1, PGpoint p2) {
        return Math.sqrt((p1.x - p2.x)*(p1.x - p2.x)+(p1.y - p2.y)*(p1.y - p2.y));
    }

    public static PGpoint toPGpoint(String data) {
        PGpoint pGpoint = new PGpoint();
        pGpoint.x = Double.parseDouble(data.substring(1,data.indexOf(',')));
        pGpoint.y = Double.parseDouble(data.substring(data.indexOf(',') + 1,data.length()-1));
        return pGpoint;
    }
}
