/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.data.filter.UserFilter;
import java.util.List;

/**
 *
 * @author edgar
 */
public interface ExtendedUserDAO {

    public List<User> findByFilter(UserFilter filter);
    
    public Long count(UserFilter filter);
}
