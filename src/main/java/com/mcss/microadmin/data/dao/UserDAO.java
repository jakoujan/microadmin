/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.data.dao;

import com.mcss.microadmin.data.entity.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface UserDAO extends CrudRepository<User, Integer>, ExtendedUserDAO {

    public User findByUsername(String username);

    public List<User> findByActive(Boolean active);
}
