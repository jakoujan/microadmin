/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Section;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author edgar
 */
public interface SectionDAO extends PagingAndSortingRepository<Section, Integer>, ExtendedSectionDAO {

    public Iterable<Section> findByActive(boolean active);

}
