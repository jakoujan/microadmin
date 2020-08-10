/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Flavor;
import com.mcss.microadmin.data.filter.FlavorFilter;
import java.util.List;

/**
 *
 * @author oscardanielrangelmartinez
 */
public interface ExtendedFlavorDAO {
    
    public List<Flavor> findByFilter(FlavorFilter filter);
    
    public Long count(FlavorFilter filter);
    
}
