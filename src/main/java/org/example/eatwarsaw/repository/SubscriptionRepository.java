package org.example.eatwarsaw.repository;

import org.example.eatwarsaw.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findBySubscriberIdAndSubscribedToId(Long subscriberId, Long subscribedToId);

    List<Subscription> findBySubscriberId(Long subscriberId);

    List<Subscription> findBySubscribedToId(Long subscribedToId);

    boolean existsBySubscriberIdAndSubscribedToId(Long subscriberId, Long subscribedToId);

    void deleteBySubscriberIdAndSubscribedToId(Long subscriberId, Long subscribedToId);
}
