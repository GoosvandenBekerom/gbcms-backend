package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Post;
import com.goosvandenbekerom.gbcms.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService extends DomainCrudService<Post, Integer, PostRepository> {}
