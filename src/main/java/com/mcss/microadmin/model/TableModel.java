/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Table;
import com.mcss.microadmin.data.filter.TableFilter;

/**
 *
 * @author edgar
 */
public interface TableModel {

    public Response save(Table table);

    public Response delete(Table table);

    public Response getTables(TableFilter filter);
    
}
