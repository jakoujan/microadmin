/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.filter.PaymentMethodFilter;
import com.mcss.microadmin.model.PaymentMethodModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payment/method")
public class PaymentMethodController {

    @Autowired
    PaymentMethodModel paymentMethodModel;
    
    @PostMapping(value = "")
    public Response paymentMethods(@RequestBody PaymentMethodFilter filter) {
        return this.paymentMethodModel.getPaymentMethods(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody PaymentMethod paymentMethod) {
        return this.paymentMethodModel.save(paymentMethod);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody PaymentMethod paymentMethod) {
        return this.paymentMethodModel.delete(paymentMethod);
    }
}
