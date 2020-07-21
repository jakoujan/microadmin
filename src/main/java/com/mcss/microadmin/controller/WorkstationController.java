/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Workstation;
import com.mcss.microadmin.data.filter.WorkstationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mcss.microadmin.model.WorkstationModel;

@RestController
@RequestMapping(value = "/api/workstations")
public class WorkstationController {

    @Autowired
    WorkstationModel workStationModel;
    
    @PostMapping(value = "")
    public Response workStations(@RequestBody WorkstationFilter filter) {
        return this.workStationModel.getWorkStations(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Workstation workStation) {
        return this.workStationModel.save(workStation);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Workstation workStation) {
        return this.workStationModel.delete(workStation);
    }
}
