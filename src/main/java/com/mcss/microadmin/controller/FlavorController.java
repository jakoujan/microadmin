/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Flavor;
import com.mcss.microadmin.data.filter.FlavorFilter;
import com.mcss.microadmin.model.FlavorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/flavors")
public class FlavorController {

    @Autowired
    FlavorModel flavorModel;
    
    @PostMapping(value = "")
    public Response flavors(@RequestBody FlavorFilter filter) {
        return this.flavorModel.getFlavors(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Flavor flavor) {
        return this.flavorModel.save(flavor);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Flavor flavor) {
        return this.flavorModel.delete(flavor);
    }
}
