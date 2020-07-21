/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.filter.SectionFilter;

/**
 *
 * @author edgar
 */
public interface SectionModel {

    public Response save(Section section);

    public Response delete(Section section);

    public Response getSections(SectionFilter filter);
    
}
