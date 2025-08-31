package com.codingnomads.springweb.resttemplate.PUT.models;


import lombok.Data;
import lombok.Getter;

@Data
public class UserResponseObject {
    User data;
    Error error;
    String status;
}
