/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Unit;
import com.mcss.microadmin.data.filter.UnitFilter;

/**
 *
 * @author edgar
 */
public interface UnitModel {

    public Response save(Unit unit);

    public Response delete(Unit unit);

    public Response getUnits(UnitFilter filter);
    
}
