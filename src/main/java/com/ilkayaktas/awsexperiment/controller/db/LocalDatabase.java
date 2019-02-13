package com.ilkayaktas.awsexperiment.controller.db;

import com.ilkayaktas.awsexperiment.model.User;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by aselsan on 12.02.2019 at 18:27.
 */
@Configuration
public class LocalDatabase implements DBController {
    private Map<Integer, User> userMap = new HashMap<>();


    @Override
    public void saveUser(User user) {
        userMap.put(user.getId(), user);
    }

    @Override
    public User getUser(Integer userId) {
        return userMap.get(userId);
    }

    @Override
    public List<User> getUsers() {
        return userMap.values().stream().collect(Collectors.toList());
    }
}
