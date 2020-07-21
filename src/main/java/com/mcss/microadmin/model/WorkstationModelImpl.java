/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.entity.Workstation;
import com.mcss.microadmin.data.filter.WorkstationFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.mcss.microadmin.data.dao.WorkstationDAO;

@Component
public class WorkstationModelImpl implements WorkstationModel {

    @Autowired
    WorkstationDAO workstationDAO;

    @Override
    @Transactional
    public Response save(Workstation workstation) {
        Response response = Response.getInstance();
        workstation.setActive(true);
        this.workstationDAO.save(workstation);
        response.setMessage("La caja se ha guardado con exito");
        response.addField(Constants.ENTITY, workstation);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Workstation workstation) {
        Response response = Response.getInstance();
        workstation.setActive(Status.INACTIVE);
        this.workstationDAO.save(workstation);
        response.setMessage("La caja se ha elimnado");
        response.addField(Constants.ENTITY, workstation);
        return response;
    }

    @Override
    public Response getWorkStations(WorkstationFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.workstationDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.workstationDAO.count(filter));
        return response;
    }

}
