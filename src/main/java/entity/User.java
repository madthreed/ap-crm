package entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class User {
    private int id;
    private String login;
    private String password;
    private ArrayList<Role> roles = new ArrayList<>();
}
