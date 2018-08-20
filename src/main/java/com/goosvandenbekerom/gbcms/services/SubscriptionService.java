package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Subscription;
import com.goosvandenbekerom.gbcms.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionService extends DomainCrudService<Subscription, Integer, SubscriptionRepository> {}
