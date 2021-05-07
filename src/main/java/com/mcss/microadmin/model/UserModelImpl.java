/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.UserDAO;
import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.data.filter.UserFilter;
import com.ispc.slibrary.dto.Response;
import com.ispc.slibrary.helper.PasswordHelper;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserModelImpl implements UserModel {

    @Autowired
    UserDAO userDAO;

    @Override
    public Response getUsers(UserFilter filter) {
        Response response = Response.getInstance();
        response.addField(Constants.DATA, this.userDAO.findByFilter(filter));
        response.addField(Constants.COUNT, this.userDAO.count(filter));
        return response;
    }

    @Override
    @Transactional
    public Response save(User user) {
        if (user.getId().equals(0)) {
            //user.setPassword(PasswordHelper.generateRandomPassword(8));
            user.setActive(Boolean.TRUE);
        }
        this.userDAO.save(user);
        Response response = Response.getInstance();
        response.addField(Constants.USER, user);
        response.addField(Constants.PASSWORD, user.getPassword());
        return response;
    }
    
    @Override
    public Response checkUsername(String username) {
        Response response = Response.getInstance();

        User user = this.userDAO.findByUsername(username);
        if(user == null) {
            response.setCode(Constants.USERNAME_EXIST_CODE);
        } else {
            response.setCode(Constants.USERNAME_NOT_EXIST_CODE);
        }
        return response;
    }

}
