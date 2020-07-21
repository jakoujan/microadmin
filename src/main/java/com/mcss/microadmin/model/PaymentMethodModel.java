/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.filter.PaymentMethodFilter;

/**
 *
 * @author edgar
 */
public interface PaymentMethodModel {

    public Response save(PaymentMethod brand);

    public Response delete(PaymentMethod brand);

    public Response getPaymentMethods(PaymentMethodFilter filter);
    
}
