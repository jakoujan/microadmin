/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Unit;
import com.mcss.microadmin.data.filter.UnitFilter;
import com.mcss.microadmin.model.UnitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/units")
public class UnitController {

    @Autowired
    UnitModel unitModel;
    
    @PostMapping(value = "")
    public Response units(@RequestBody UnitFilter filter) {
        return this.unitModel.getUnits(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Unit unit) {
        return this.unitModel.save(unit);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Unit unit) {
        return this.unitModel.delete(unit);
    }
}
