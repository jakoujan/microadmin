/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.Sequence;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SequenceDAO extends CrudRepository<Sequence, String> {
    
    @Override
    public List<Sequence> findAll();
    
}
