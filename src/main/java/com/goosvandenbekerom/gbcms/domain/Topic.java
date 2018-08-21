package com.goosvandenbekerom.gbcms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goosvandenbekerom.gbcms.Constants;
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
    @Column(unique = true, length = Constants.MAX_TOPIC_LENGTH)
    private String name;
    private Date created;
    private Date updated;
    private boolean secured;

    @JsonIgnore
    @OneToMany(mappedBy = "topic")
    private List<Post> posts;

    public Topic(String name, boolean secured) {
        this.name = name;
        this.secured = secured;
    }

    @PrePersist
    public void onPrePersist() {
        this.created = new Date();
        this.updated = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated = new Date();
    }
}
