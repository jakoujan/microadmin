/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Flavor;
import com.mcss.microadmin.data.filter.FlavorFilter;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author oscardanielrangelmartinez
 */
public interface FlavorDAO extends PagingAndSortingRepository<Flavor, Integer>, ExtendedFlavorDAO{ 
     public void findByActive(boolean active);      
}
