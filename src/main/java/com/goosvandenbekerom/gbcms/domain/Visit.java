package com.goosvandenbekerom.gbcms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToOne
    @JsonIgnoreProperties({"created", "updated", "summary", "content", "visited", "topic" })
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
