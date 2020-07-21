/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.StockDAO;
import com.mcss.microadmin.data.entity.Stock;
import com.mcss.microadmin.data.filter.StockFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockModelImpl implements StockModel {

    @Autowired
    StockDAO stockDAO;

    @Override
    @Transactional
    public Response save(Stock stock) {
        Response response = Response.getInstance();
        this.stockDAO.save(stock);
        response.setMessage("El inventario del producto se ha guardado con exito");
        response.addField(Constants.ENTITY, stock);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Stock stock) {
        Response response = Response.getInstance();
        this.stockDAO.save(stock);
        response.setMessage("La tienda se ha elimnado");
        response.addField(Constants.ENTITY, stock);
        return response;
    }

    @Override
    public Response getStocks(StockFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.stockDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.stockDAO.count(filter));
        return response;
    }

    @Override
    public Response getInitialStocks(StockFilter filter) {
        return null;
    }

}
