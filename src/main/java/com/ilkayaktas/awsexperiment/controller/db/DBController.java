package com.ilkayaktas.awsexperiment.controller.db;

import com.ilkayaktas.awsexperiment.model.User;

import java.util.List;

/**
 * Created by aselsan on 12.02.2019 at 18:25.
 */

public interface DBController {
    void saveUser(User user);
    User getUser(Integer userId);
    List<User> getUsers();
}
