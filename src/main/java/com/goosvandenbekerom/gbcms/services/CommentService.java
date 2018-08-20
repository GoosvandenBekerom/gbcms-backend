package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Comment;
import com.goosvandenbekerom.gbcms.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService extends DomainCrudService<Comment, Long, CommentRepository> {}
