package com.goosvandenbekerom.gbcms.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
public @Data class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private Date date;

    public Subscription(String email) {
        this.email = email;
    }

    @PrePersist
    public void onPrePersist() {
        this.date = new Date();
    }
}
