package com.dashwood.modules.datax.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
}
