/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.UnitDAO;
import com.mcss.microadmin.data.entity.Unit;
import com.mcss.microadmin.data.filter.UnitFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UnitModelImpl implements UnitModel {

    @Autowired
    UnitDAO unitDAO;

    @Override
    @Transactional
    public Response save(Unit unit) {
        Response response = Response.getInstance();
        this.unitDAO.save(unit);
        response.setMessage("La unidad de medida se ha guardado con exito");
        response.addField(Constants.ENTITY, unit);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Unit unit) {
        Response response = Response.getInstance();
        unit.setActive(Status.INACTIVE);
        this.unitDAO.save(unit);
        response.setMessage("La unidad de medida se ha elimnado");
        response.addField(Constants.ENTITY, unit);
        return response;
    }

    @Override
    public Response getUnits(UnitFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.unitDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.unitDAO.count(filter));
        return response;
    }

}
