package com.goosvandenbekerom.gbcms.repositories;

import com.goosvandenbekerom.gbcms.domain.Subscription;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, Integer> {
    boolean existsByEmail(String email);

    Optional<Subscription> findFirstByEmail(String email);
}
