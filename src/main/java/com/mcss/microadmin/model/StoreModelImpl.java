/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.StoreDAO;
import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.filter.StoreFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StoreModelImpl implements StoreModel {

    @Autowired
    StoreDAO storeDAO;

    @Override
    @Transactional
    public Response save(Store store) {
        Response response = Response.getInstance();
        this.storeDAO.save(store);
        response.setMessage("La tienda se ha guardado con exito");
        response.addField(Constants.ENTITY, store);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Store store) {
        Response response = Response.getInstance();
        store.setActive(Status.INACTIVE);
        this.storeDAO.save(store);
        response.setMessage("La tienda se ha elimnado");
        response.addField(Constants.ENTITY, store);
        return response;
    }

    @Override
    public Response getStores(StoreFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.storeDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.storeDAO.count(filter));
        return response;
    }

}
