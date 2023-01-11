package com.projet.pimpon.emergency.controller.gateway;

import com.projet.pimpon.emergency.dtos.dto.TeamDto;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutputBoundChannel")
public interface MqttGateway {
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, TeamDto data);
}

