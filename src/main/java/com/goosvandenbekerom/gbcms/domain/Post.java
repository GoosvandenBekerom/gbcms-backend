package com.goosvandenbekerom.gbcms.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
public @Data class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date created;
    private Date updated;
    private String title;
    private String summary;
    @Lob
    private String content;

    @ManyToOne
    private Topic topic;
    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Visit> visits;
    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post(String title, String summary, String content, Topic topic) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.topic = topic;
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
