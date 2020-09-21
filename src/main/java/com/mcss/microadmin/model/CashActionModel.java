/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.CashAction;
import com.mcss.microadmin.data.filter.CashActionFilter;

/**
 *
 * @author edgar
 */
public interface CashActionModel {

    public Response save(CashAction cashAction);

    public Response delete(CashAction cashAction);

    public Response getCashActions(CashActionFilter filter);
    
    public Response getCashAction(Integer id);
    
}
