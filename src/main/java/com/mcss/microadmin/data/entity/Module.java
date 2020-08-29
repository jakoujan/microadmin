/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.entity;

import com.mcss.microadmin.data.converter.SubModulesConverter;
import java.util.List;
import javax.persistence.Convert;

/**
 *
 * @author edgar
 */
public class Module {

    private String id;
    private List<SubModule> submodules;

    public Module() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Convert(converter = SubModulesConverter.class)
    public List<SubModule> getSubmodules() {
        return submodules;
    }

    public void setSubmodules(List<SubModule> submodules) {
        this.submodules = submodules;
    }

}
