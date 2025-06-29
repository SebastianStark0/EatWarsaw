package org.example.eatwarsaw.service;

import lombok.RequiredArgsConstructor;
import org.example.eatwarsaw.dto.UserShortDto;
import org.example.eatwarsaw.mapper.UserMapper;
import org.example.eatwarsaw.model.Subscription;
import org.example.eatwarsaw.model.user.User;
import org.example.eatwarsaw.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    public void subscribe(Long targetUserId) {
        User currentUser = userService.getCurrentUser();

        if (currentUser.getId().equals(targetUserId)) {
            throw new IllegalArgumentException("Cannot subscribe to yourself");
        }
        if (subscriptionRepository.existsBySubscriberIdAndSubscribedToId(currentUser.getId(), targetUserId)) {
            throw new IllegalStateException("Already subscribed");
        }

        User targetUser = userService.findUserById(targetUserId);

        Subscription subscription = Subscription.builder()
                .subscriber(currentUser)
                .subscribedTo(targetUser)
                .subscribedAt(LocalDateTime.now())
                .build();
        subscriptionRepository.save(subscription);
    }

    public void unsubscribe(Long targetUserId) {
        User currentUser = userService.getCurrentUser();
        subscriptionRepository.deleteBySubscriberIdAndSubscribedToId(currentUser.getId(), targetUserId);
    }

    public List<UserShortDto> getMySubscriptions() {
        User currentUser = userService.getCurrentUser();
        return subscriptionRepository.findBySubscriberId(currentUser.getId()).stream()
                .map(s -> userMapper.toShortDto(s.getSubscribedTo()))
                .toList();
    }

    public List<UserShortDto> getMySubscribers() {
        User currentUser = userService.getCurrentUser();
        return subscriptionRepository.findBySubscribedToId(currentUser.getId()).stream()
                .map(s -> userMapper.toShortDto(s.getSubscriber()))
                .toList();
    }

    public boolean isSubscribed(Long targetUserId) {
        User currentUser = userService.getCurrentUser();
        return subscriptionRepository.existsBySubscriberIdAndSubscribedToId(currentUser.getId(), targetUserId);
    }
}


