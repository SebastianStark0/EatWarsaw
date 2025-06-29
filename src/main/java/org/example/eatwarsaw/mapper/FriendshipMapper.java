package org.example.eatwarsaw.mapper;

import lombok.RequiredArgsConstructor;
import org.example.eatwarsaw.dto.FriendshipDto;
import org.example.eatwarsaw.model.Friendship;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FriendshipMapper {

    private final UserMapper userMapper;

    public FriendshipDto toDto(Friendship friendship) {
        return new FriendshipDto(
                friendship.getId(),
                userMapper.toShortDto(friendship.getUser()),
                userMapper.toShortDto(friendship.getFriend()),
                friendship.getCreatedAt(),
                friendship.getStatus()
        );
    }
}
