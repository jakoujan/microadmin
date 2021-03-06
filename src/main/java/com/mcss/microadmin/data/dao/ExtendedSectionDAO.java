/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.filter.SectionFilter;

/**
 *
 * @author edgar
 */
interface ExtendedSectionDAO {

    public Iterable<Section> findByFilter(SectionFilter filter);
    
    public Long count(SectionFilter filter);
}
