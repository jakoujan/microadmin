/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.filter.StoreFilter;

/**
 *
 * @author edgar
 */
public interface StoreModel {

    public Response save(Store store);

    public Response delete(Store store);

    public Response getStores(StoreFilter filter);
    
}
