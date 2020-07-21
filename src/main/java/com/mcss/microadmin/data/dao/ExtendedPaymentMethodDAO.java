/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.PaymentMethod;
import com.mcss.microadmin.data.filter.PaymentMethodFilter;

/**
 *
 * @author edgar
 */
interface ExtendedPaymentMethodDAO {

    public Iterable<PaymentMethod> findByFilter(PaymentMethodFilter filter);

    public Long count(PaymentMethodFilter filter);
}
