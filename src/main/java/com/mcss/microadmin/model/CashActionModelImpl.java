/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.CashActionDAO;
import com.mcss.microadmin.data.entity.CashAction;
import com.mcss.microadmin.data.filter.CashActionFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashActionModelImpl implements CashActionModel {

    @Autowired
    CashActionDAO cashActionDAO;

    @Override
    @Transactional
    public Response save(CashAction cashAction) {
        Response response = Response.getInstance();
        this.cashActionDAO.save(cashAction);
        if(cashAction.getAction_status() == 1){
            response.setMessage("La caja se ha abierto con el monto de: "+cashAction.getInitial_amount());
        }else{
            response.setMessage("La caja se ha cerrado");
        }
        response.addField(Constants.ENTITY, cashAction);
        return response;
    }

    @Override
    @Transactional
    public Response delete(CashAction cashAction) {
        Response response = Response.getInstance();
        cashAction.setActive(Status.INACTIVE);
        this.cashActionDAO.save(cashAction);
        response.setMessage("La marca se ha elimnado");
        response.addField(Constants.ENTITY, cashAction);
        return response;
    }

    @Override
    public Response getCashActions(CashActionFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.cashActionDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.cashActionDAO.count(filter));
        return response;
    }
    
    @Override
    public Response getCashAction(Integer id) {
        Response response = Response.getInstance();
        response.addField(Constants.ENTITY, this.cashActionDAO.findById(id).get());
        return response;
    }

}
