/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.filter.WorkstationFilter;
import com.mcss.microadmin.data.view.WorkstationView;

/**
 *
 * @author edgar
 */
interface ExtendedWorkstationDAO {

    public Iterable<WorkstationView> findByFilter(WorkstationFilter filter);

    public Long count(WorkstationFilter filter);
}
