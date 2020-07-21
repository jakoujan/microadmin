/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Unit;
import com.mcss.microadmin.data.filter.UnitFilter;

/**
 *
 * @author edgar
 */
interface ExtendedUnitDAO {

    public Iterable<Unit> findByFilter(UnitFilter filter);

    public Long count(UnitFilter filter);
}
