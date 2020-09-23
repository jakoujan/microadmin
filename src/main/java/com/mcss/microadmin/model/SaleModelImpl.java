/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.SaleDAO;
import com.mcss.microadmin.data.dao.OrderDAO;
import com.mcss.microadmin.data.entity.Order;
import com.mcss.microadmin.data.entity.Sale;
import com.mcss.microadmin.data.entity.OrderSale;
import com.mcss.microadmin.data.filter.SaleFilter;
import java.util.Date;
//import com.mcss.microadmin.data.filter.SaleViewFilter;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleModelImpl implements SaleModel {
    private static final Logger LOGGER = LoggerFactory.getLogger(SaleModelImpl.class);
    
    @Autowired
    SaleDAO saleDAO;
    
    @Autowired
    OrderDAO productDAO;
   
    @Override
    @Transactional
    public Response save(Sale sale) {
        Response response = Response.getInstance();
        sale.getOrder().setSale(sale);
        this.saleDAO.save(sale);
        response.setMessage("La orden se ha generado con exito");
        response.addField(Constants.ENTITY, sale);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Sale sale) {
        Response response = Response.getInstance();
        //sale.setActive(Status.INACTIVE);
        this.saleDAO.delete(sale);
        response.setMessage("La orden se ha elimnado");
        response.addField(Constants.ENTITY, sale);
        return response;
    }

   /* @Override
    public Response getSales(SaleViewFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.saleDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.saleDAO.count(filter));
        return response;
    }*/
    
    @Override
    public Response getSale(Integer id) {
        Response response = Response.getInstance();
        response.addField(Constants.ENTITY, this.saleDAO.findById(id).get());
        return response;
    }
    
    @Override
    public Boolean createSaleFromOrder(Order order){
        Sale sale = new Sale();
        OrderSale os = new OrderSale();
        
        
        os.setOrder(order);
        os.setSale(sale);
        
        sale.setOrder(os);
        sale.setQuantity(order.getTotal_amount());
        sale.setSale_date(new Date());
        sale.setTotal_amount(order.getTotal_amount());
        
        try {
            this.saleDAO.save(sale);
        } catch (Exception e) {
            LOGGER.error("error al guardar la venta", e);
            return false;
        }
        
        return true;
        
    }

}
