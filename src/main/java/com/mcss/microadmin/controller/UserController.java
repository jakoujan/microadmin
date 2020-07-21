/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.controller;

import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.data.filter.UserFilter;
import com.mcss.microadmin.model.UserModel;
import com.ispc.slibrary.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/users/")
public class UserController {

    @Autowired
    UserModel userModel;

    @PostMapping(value = "")
    public Response getUsers(@RequestBody UserFilter filter) {
        return this.userModel.getUsers(filter);
    }

    @PostMapping(value = "save")
    public Response save(@RequestBody User user) {
        return this.userModel.save(user);
    }
    
    @RequestMapping(value="/check/username", method = {RequestMethod.GET})
    public Response checkUserName(@RequestParam("username") String username) {
        return this.userModel.checkUsername(username);
    }
}
