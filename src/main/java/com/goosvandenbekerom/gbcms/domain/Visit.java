package com.goosvandenbekerom.gbcms.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
public @Data class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ipaddress;
    private Date date;
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    public Visit(String ipaddress, Post post) {
        this.ipaddress = ipaddress;
        this.post = post;
    }

    @PrePersist
    public void onPrePersist() {
        this.date = new Date();
    }
}
