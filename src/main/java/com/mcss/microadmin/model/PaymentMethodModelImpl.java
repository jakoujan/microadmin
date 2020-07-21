/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.PaymentMethodDAO;
import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.filter.PaymentMethodFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodModelImpl implements PaymentMethodModel {

    @Autowired
    PaymentMethodDAO paymentMethodDAO;

    @Override
    @Transactional
    public Response save(PaymentMethod paymentMethod) {
        Response response = Response.getInstance();
        this.paymentMethodDAO.save(paymentMethod);
        response.setMessage("El metodo de pago se ha guardado con exito");
        response.addField(Constants.ENTITY, paymentMethod);
        return response;
    }

    @Override
    @Transactional
    public Response delete(PaymentMethod paymentMethod) {
        Response response = Response.getInstance();
        paymentMethod.setActive(Status.INACTIVE);
        this.paymentMethodDAO.save(paymentMethod);
        response.setMessage("El metodo de pago se ha elimnado");
        response.addField(Constants.ENTITY, paymentMethod);
        return response;
    }

    @Override
    public Response getPaymentMethods(PaymentMethodFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.paymentMethodDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.paymentMethodDAO.count(filter));
        return response;
    }

}
