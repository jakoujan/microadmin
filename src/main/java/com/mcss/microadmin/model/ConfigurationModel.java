/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.data.dto.ConfigurationDTO;
import com.ispc.slibrary.dto.Response;

/**
 *
 * @author edgar
 */
public interface ConfigurationModel {

    public Response getConfiguration();

    public Response save(ConfigurationDTO configuration);
    
}
