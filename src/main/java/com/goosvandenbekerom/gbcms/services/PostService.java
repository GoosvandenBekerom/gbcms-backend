package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Post;
import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends DomainCrudService<Post, Integer, PostRepository> {
    public Post update(Post post, String title, String summary, String content, Topic topic) {
        if (!title.isEmpty()) post.setTitle(title);
        if (!summary.isEmpty()) post.setSummary(summary);
        if (!content.isEmpty()) post.setContent(content);
        post.setTopic(topic);
        return repo.save(post);
    }

    public List<Post> findAllNotArchived() {
        return repo.findAllByArchived(false);
    }

    public List<Post> findAllArchived() {
        return repo.findAllByArchived(true);
    }

    public Post archive(Post post) {
        post.setArchived(true);
        return repo.save(post);
    }

    public Post unArchive(Post post) {
        post.setArchived(false);
        return repo.save(post);
    }
}
