package com.ilkayaktas.awsexperiment.controller.web;

import com.ilkayaktas.awsexperiment.controller.db.DBController;
import com.ilkayaktas.awsexperiment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by aselsan on 12.02.2019 at 18:23.
 */

@RestController
public class WebController {

    private DBController database;
    private AtomicInteger atomicInteger;

    @Autowired
    public WebController(DBController database) {
        this.database = database;
        this.atomicInteger = new AtomicInteger(1000);
    }

    @RequestMapping(value = "users")
    public List<User> getAllUsers(){
        return database.getUsers();
    }

    @RequestMapping(value = "add")
    public String saveUser(@RequestParam("id") Integer id, @RequestParam("name") String name, @RequestParam("address") String address){
        database.saveUser(new User(id, name, address));
        return "OK";
    }

    @RequestMapping(value = "random")
    public String addRandomUser(){
        database.saveUser(new User(atomicInteger.getAndIncrement(), "Name_"+UUID.randomUUID(), "Address_"+UUID.randomUUID()));
        return "OK";
    }
}
