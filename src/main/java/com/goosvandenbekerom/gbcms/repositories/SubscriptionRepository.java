package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.Subscription;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    boolean existsByEmail(String email);
    Optional<Subscription> findFirstByEmail(String email);
}
