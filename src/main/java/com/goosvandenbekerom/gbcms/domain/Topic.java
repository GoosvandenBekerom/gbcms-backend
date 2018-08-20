package com.goosvandenbekerom.gbcms.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, length = 30)
    private String name;
    private Date created;
    private Date updated;
    private boolean secured;

    @OneToMany(mappedBy = "topic")
    private List<Post> posts;

    public Topic(String name, boolean secured) {
        this.name = name;
        this.secured = secured;
        this.created = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated = new Date();
    }
}
