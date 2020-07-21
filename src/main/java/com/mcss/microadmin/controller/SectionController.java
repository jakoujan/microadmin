/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.ispc.slibrary.dto.Response;
import com.mcss.microadmin.data.entity.Section;
import com.mcss.microadmin.data.filter.SectionFilter;
import com.mcss.microadmin.model.SectionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/sections")
public class SectionController {

    @Autowired
    SectionModel sectionModel;
    
    @PostMapping(value = "")
    public Response sections(@RequestBody SectionFilter filter) {
        return this.sectionModel.getSections(filter);
    }

    @PostMapping(value = "/save")
    public Response save(@RequestBody Section section) {
        return this.sectionModel.save(section);
    }
    
    @PostMapping(value = "/delete")
    public Response delete(@RequestBody Section section) {
        return this.sectionModel.delete(section);
    }
}
