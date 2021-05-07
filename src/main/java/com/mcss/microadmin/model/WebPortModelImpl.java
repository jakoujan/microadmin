package com.mcss.microadmin.model;

import com.mcss.microadmin.data.dto.Configurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Component;

@Component
public class WebPortModelImpl implements WebPortModel {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebPortModelImpl.class);

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public void setPortConfigurator(Configurator configurator) {
        this.messagingTemplate.convertAndSend(configurator.getTopic(), new Object());
    }

}
