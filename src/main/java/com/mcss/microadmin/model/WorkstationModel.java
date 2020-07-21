/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Workstation;
import com.mcss.microadmin.data.filter.WorkstationFilter;

/**
 *
 * @author edgar
 */
public interface WorkstationModel {

    public Response save(Workstation workStation);

    public Response delete(Workstation workStation);

    public Response getWorkStations(WorkstationFilter filter);
    
}
