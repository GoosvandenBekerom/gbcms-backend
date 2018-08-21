package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {}
