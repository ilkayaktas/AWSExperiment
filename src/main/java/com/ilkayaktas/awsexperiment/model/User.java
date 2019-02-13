package com.ilkayaktas.awsexperiment.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by aselsan on 12.02.2019 at 18:19.
 */

@ToString
public class User {
    @Getter @Setter
    private Integer id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String address;

    public User(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
