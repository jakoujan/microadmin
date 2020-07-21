/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.Status;
import com.mcss.microadmin.data.dao.SectionDAO;
import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.filter.SectionFilter;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SectionModelImpl implements SectionModel {

    @Autowired
    SectionDAO sectionDAO;

    @Override
    @Transactional
    public Response save(Section section) {
        Response response = Response.getInstance();
        this.sectionDAO.save(section);
        response.setMessage("La sección se ha guardado con exito");
        response.addField(Constants.ENTITY, section);
        return response;
    }

    @Override
    @Transactional
    public Response delete(Section section) {
        Response response = Response.getInstance();
        section.setActive(Status.INACTIVE);
        this.sectionDAO.save(section);
        response.setMessage("La sección se ha elimnado");
        response.addField(Constants.ENTITY, section);
        return response;
    }

    @Override
    public Response getSections(SectionFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.sectionDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.sectionDAO.count(filter));
        return response;
    }

}
