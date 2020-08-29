/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Country;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author edgar
 */
public interface CountryDAO extends CrudRepository<Country, Integer> {

}
