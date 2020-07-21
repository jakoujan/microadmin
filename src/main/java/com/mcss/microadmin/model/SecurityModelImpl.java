/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mcss.microadmin.model;

import com.mcss.microadmin.Constants;
import com.mcss.microadmin.data.dao.UserDAO;
import com.mcss.microadmin.data.entity.User;
import com.ispc.slibrary.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Base64;


@Component
public class SecurityModelImpl implements SecurityModel {

    private static final String PASSWORD_INCORRECT_MESSAGE = "El password es incorrecto";
    private static final String INVALID_CREDENTIALS_MESSAGE = "Credenciales invalidas";

    @Autowired
    UserDAO userDAO;
    
    @Override
    public Response login(User user) {
        Response response = Response.getInstance();

        User testUser = this.userDAO.findByUsername(user.getUsername());
        if (testUser == null) {
            response.setStatus(Response.RESPONSE_NOT_OK);
            response.setCode(Constants.USER_NOT_FOUND_CODE);
            response.setMessage(INVALID_CREDENTIALS_MESSAGE);
        } else if (testUser.getPassword().equals(user.getPassword())) {
            testUser.setPassword(Constants.EMPTY);
            response.setStatus(Response.RESPONSE_OK);
            response.addField(Constants.USER, testUser);

            String plainClientCredentials = user.getUsername()+ ":" + user.getPassword();
            String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
            response.addField(Constants.TOKEN, base64ClientCredentials);
        } else {
            response.setStatus(Response.RESPONSE_NOT_OK);
            response.setCode(Constants.USER_INCORRECT_PASSWORD_CODE);
            response.setMessage(PASSWORD_INCORRECT_MESSAGE);
        }

        return response;
    }

}
