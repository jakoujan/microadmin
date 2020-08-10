/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Flavor;
import com.mcss.microadmin.data.filter.FlavorFilter;

/**
 *
 * @author edgar
 */
public interface FlavorModel {

    public Response save(Flavor flavor);

    public Response delete(Flavor flavor);

    public Response getFlavors(FlavorFilter filter);
    
}
