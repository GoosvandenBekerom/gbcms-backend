package com.goosvandenbekerom.gbcms.services;

import com.goosvandenbekerom.gbcms.domain.Subscription;
import com.goosvandenbekerom.gbcms.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionService extends DomainCrudService<Subscription, Integer, SubscriptionRepository> {

    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }

    public Optional<Subscription> findByEmail(String email) {
        return repo.findFirstByEmail(email);
    }
}
