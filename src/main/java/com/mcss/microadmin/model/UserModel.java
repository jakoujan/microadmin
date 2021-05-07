package com.mcss.microadmin.model;

import com.mcss.microadmin.data.entity.User;
import com.mcss.microadmin.data.filter.UserFilter;
import com.ispc.slibrary.dto.Response;

public interface UserModel {

    public Response getUsers(UserFilter filter);

    public Response save(User user);

    public Response checkUsername(String username);
    
}
