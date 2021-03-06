package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Post;
import com.goosvandenbekerom.gbcms.domain.Topic;
import com.goosvandenbekerom.gbcms.repositories.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostService extends DomainCrudService<Post, Integer, PostRepository> {
    public Post update(Post post, String title, String summary, String content, Topic topic) {
        if (!title.isEmpty()) post.setTitle(title);
        if (!summary.isEmpty()) post.setSummary(summary);
        if (!content.isEmpty()) post.setContent(content);
        post.setTopic(topic);
        return repo.save(post);
    }

    public Page<Post> findAllNotArchived(Pageable pageable) {
        return repo.findAllByArchived(false, pageable);
    }

    public Page<Post> findAllArchived(Pageable pageable) {
        return repo.findAllByArchived(true, pageable);
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
