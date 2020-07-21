/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Workstation;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author edgar
 */
public interface WorkstationDAO extends PagingAndSortingRepository<Workstation, Integer>, ExtendedWorkstationDAO {

    public Iterable<Workstation> findByActive(boolean active);

}
