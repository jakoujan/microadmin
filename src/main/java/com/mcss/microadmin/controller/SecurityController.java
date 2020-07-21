/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.model.SecurityModel;
import com.ispc.slibrary.dto.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security/")
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    SecurityModel securityModel;

    @PostMapping(value = "login")
    public Response login(@RequestBody User user) {
        LOGGER.info("Usuario a logearse: " + user);
        return this.securityModel.login(user);
    }

}
