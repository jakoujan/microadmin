/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Store;
import com.mcss.microadmin.data.filter.StoreFilter;
import com.mcss.microadmin.model.StoreModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/stores")
public class StoreController {

    @Autowired
    StoreModel storeModel;
    
    @PostMapping(value = "")
    public Response stores(@RequestBody StoreFilter filter) {
        return this.storeModel.getStores(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Store store) {
        return this.storeModel.save(store);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Store store) {
        return this.storeModel.delete(store);
    }
}
