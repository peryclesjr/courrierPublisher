package com.courrier.courrierpublish.service.impl;


import com.courrier.courrierpublish.service.IMessagePublisherService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;

@Service
public class MessagePublisherService implements IMessagePublisherService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private  HashMap<Integer, String> mapUuid = new HashMap<>();
    private  HashMap<Integer, String> mapCourrierUuid = new HashMap<>();

    private final String exchangeName = "courrierchallenge";


    public void publishCreated(String message) {
        var uuid = UUID.randomUUID().toString();
        var courrierUUID = UUID.randomUUID().toString();
        System.out.println("uuid: " + uuid);
        double value = 10.0;
        message = String.format(""" 
                    {
                    "deliveryId":%s,
                    "courierId":"asdfasdfsadf89789r123",
                    "createdTimestamp":"1703766503",
                    "value":%f
                    } 
                """, uuid, value);


        for (int i = 0; i < 200; i++) {
            if (i % 10 == 0) {
                uuid = UUID.randomUUID().toString();
                mapUuid.put(Integer.valueOf(i), uuid);
                mapCourrierUuid.put(Integer.valueOf(i), courrierUUID);
                message = String.format(""" 
                            {
                            "deliveryId":"%s",
                            "courierId":"%s",
                            "createdTimestamp":%s,
                            "value":%f
                            } 
                        """, uuid,
                            courrierUUID,
                            Instant.now().getEpochSecond(),
                        value + 1.0);

            }
            rabbitTemplate.convertAndSend("courrierchallenge", "createdDelivery", message);
        }
    }

    public void publishToBonusModified (String message){
        var value = 7.0;
        var uuidBonus = UUID.randomUUID().toString();
        for (int i = 0; i < 200; i++) {
            if (i % 10 == 0) {
                uuidBonus = UUID.randomUUID().toString();
                message = String.format(""" 
                            {
                            "bonusId": "%s",
                            "deliveryId":"%s",
                            "courierId":"%s",
                            "createdTimestamp":%s,
                            "value":%f
                            } 
                        """,  uuidBonus,
                        mapUuid.get(i),
                        mapCourrierUuid.get(i),
                        Instant.now().getEpochSecond(),
                        (value + 1.0) * Math.random() * 10);

            }
            rabbitTemplate.convertAndSend("courrierchallenge", "adjustmentModified", message);
        }

    }

    public void publishToAdjustmentModified (String message){
        var value = -1.0;
        var uuidAdjust = UUID.randomUUID().toString();
        for (int i = 0; i < 200; i++) {
            if (i % 10 == 0) {

                uuidAdjust = UUID.randomUUID().toString();
                message = String.format(""" 
                            {
                            "adjustmentId": "%s",
                            "deliveryId": "%s",
                            "courierId":"%s",
                            "createdTimestamp":%s,
                            "value":%f
                            } 
                        """, uuidAdjust,
                            mapUuid.get(i),
                            mapCourrierUuid.get(i),
                            Instant.now().getEpochSecond(),
                            (value + 1.0) * Math.random() * 10);

            }
            rabbitTemplate.convertAndSend(exchangeName, "bonusModified", message);
        }
    }

    @Override
    public void publishMessage(String message) {
        publishCreated(message);
        publishToAdjustmentModified(message);
        publishToBonusModified(message);
    }


}