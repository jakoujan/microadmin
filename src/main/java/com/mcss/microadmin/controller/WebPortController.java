/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.dto.Configurator;
import com.mcss.microadmin.model.WebPortModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
public class WebPortController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebPortController.class);

    @Autowired
    WebPortModel webPortModel;

    @MessageMapping("/order")
    public void setMonitor(@Payload Configurator configurator) {
        this.webPortModel.setPortConfigurator(configurator);
    }
}
