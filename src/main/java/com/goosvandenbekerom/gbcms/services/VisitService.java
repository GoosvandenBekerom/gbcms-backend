package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Visit;
import com.goosvandenbekerom.gbcms.repositories.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService extends DomainCrudService<Visit, Long, VisitRepository> {
}
