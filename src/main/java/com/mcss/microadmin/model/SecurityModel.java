/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.data.entity.User;
import com.ispc.slibrary.dto.Response;

/**
 *
 * @author edgar
 */
public interface SecurityModel {

    public Response login(User user);

}
