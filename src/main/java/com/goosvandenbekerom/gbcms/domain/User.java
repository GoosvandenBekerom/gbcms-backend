package com.goosvandenbekerom.gbcms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goosvandenbekerom.gbcms.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public @Data class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = Constants.MAX_USERNAME_LENGTH)
    private String username;
    @JsonIgnore
    private String password;
    private String firstname;
    private String lastname;

    public User(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
