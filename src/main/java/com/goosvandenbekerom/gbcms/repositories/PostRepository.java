package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {
    Page<Post> findAllByArchived(boolean archived, Pageable pageable);
}
