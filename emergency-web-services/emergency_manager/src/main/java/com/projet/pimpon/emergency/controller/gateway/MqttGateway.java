package com.projet.pimpon.emergency.controller.gateway;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "mqttInputBoundChannel")
public interface MqttGateway {
    String getMqttMessage();
    //void sendToMqtt(AccidentDto data, @Header(MqttHeaders.TOPIC) String topic);
}

