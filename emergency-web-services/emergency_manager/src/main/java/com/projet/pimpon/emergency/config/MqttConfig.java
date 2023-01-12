package com.projet.pimpon.emergency.config;

import com.projet.pimpon.emergency.service.AccidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

@Configuration
@Slf4j
@Component
@RequiredArgsConstructor
public class MqttConfig {

    public static String SIMULATOR_EMERGENCY = "simulatorEmergency";
    public static String GET_ACCIDENT = "emergencyAccidents";
    public static String EMERGENCY_SIMULATOR = "emergencySimulator";
    @Autowired
    private Environment environment;
    private final AccidentService accidentService;

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[] {
                environment.getProperty("mqtt.address")
        });
        options.setUserName(environment.getProperty("mqtt.username"));
        options.setPassword(environment.getProperty("mqtt.password").toCharArray());
        options.setCleanSession(true);

        factory.setConnectionOptions(options);

        return factory;
    }

    @Bean
    public MessageChannel mqttInputBoundChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter("serverIn",mqttPahoClientFactory(),"#");
        adapter.setCompletionTimeout(60000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(2);
        adapter.setOutputChannel(mqttInputBoundChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputBoundChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
                if(topic.equals(GET_ACCIDENT)) {
                    log.info(message.getPayload().toString());
                    accidentService.manageAlerts(message.getPayload().toString());
                }
                else if(topic.equals(EMERGENCY_SIMULATOR)) {
                    log.info(message.getPayload().toString());
                }
                else if(topic.equals(SIMULATOR_EMERGENCY)) {

                }
            }
        };
    }

    @Bean
    public MessageChannel mqttOutBoundChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutputBoundChannel")
    public MessageHandler mqttOutBound() {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler("serverOut", mqttPahoClientFactory());

        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic("#");
        return messageHandler;
    }
}
