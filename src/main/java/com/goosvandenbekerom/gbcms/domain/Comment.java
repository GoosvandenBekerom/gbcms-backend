package com.goosvandenbekerom.gbcms.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
public @Data
class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date date;
    private String message;

    @ManyToOne
    @JsonIgnoreProperties({"created", "updated", "summary", "content", "visited", "topic"})
    private Post post;

    public Comment(String name, String message, Post post) {
        this.name = name;
        this.message = message;
        this.post = post;
    }

    @PrePersist
    public void onPrePersist() {
        this.date = new Date();
    }
}
