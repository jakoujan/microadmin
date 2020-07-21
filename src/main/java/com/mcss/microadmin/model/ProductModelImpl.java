/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.ProductDAO;
import com.mcss.microadmin.data.entity.Product;

import com.mcss.microadmin.data.filter.ProductViewFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductModelImpl implements ProductModel {

    @Autowired
    ProductDAO productDAO;

    @Override
    @Transactional
    public Response save(Product product) {
        Response response = Response.getInstance();
        this.productDAO.save(product);
        response.setMessage("El producto se ha guardado con exito");
        response.addField(Constants.ENTITY, product);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Product product) {
        Response response = Response.getInstance();
        product.setActive(Status.INACTIVE);
        this.productDAO.save(product);
        response.setMessage("El producto se ha elimnado");
        response.addField(Constants.ENTITY, product);
        return response;
    }

    @Override
    public Response getProducts(ProductViewFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.productDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.productDAO.count(filter));
        return response;
    }

    @Override
    public Response getProduct(Integer id) {
        Response response = Response.getInstance();
        response.addField(Constants.ENTITY, this.productDAO.findById(id).get());
        return response;
    }

}
