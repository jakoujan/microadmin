/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.TableDAO;
import com.mcss.microadmin.data.entity.Table;
import com.mcss.microadmin.data.filter.TableFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TableModelImpl implements TableModel {

    @Autowired
    TableDAO tableDAO;

    @Override
    @Transactional
    public Response save(Table table) {
        Response response = Response.getInstance();
        if(table.getId() == null) {
            table.setActive(Boolean.TRUE);
        }
        this.tableDAO.save(table);
        response.setMessage("La mesa se ha guardado con exito");
        response.addField(Constants.ENTITY, table);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Table table) {
        Response response = Response.getInstance();
        table.setActive(Status.INACTIVE);
        this.tableDAO.save(table);
        response.setMessage("La mesa se ha elimnado");
        response.addField(Constants.ENTITY, table);
        return response;
    }

    @Override
    public Response getTables(TableFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.tableDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.tableDAO.count(filter));
        return response;
    }

}
