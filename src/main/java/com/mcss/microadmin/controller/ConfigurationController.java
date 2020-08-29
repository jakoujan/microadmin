/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.dto.ConfigurationDTO;
import com.ispc.slibrary.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mcss.microadmin.model.ConfigurationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping(value = "/api/configuration/")
public class ConfigurationController {
    
    @Autowired
    ConfigurationModel configurationModel;
    
    @GetMapping(value = "")
    public Response getConfiguration() {
        return this.configurationModel.getConfiguration();
    }
    
    @PostMapping(value = "save")
    public Response save(@RequestBody ConfigurationDTO configuration) {
        return this.configurationModel.save(configuration);
    }
}
