package org.example.eatwarsaw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eatwarsaw.enums.FriendshipStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipDto {
    private Long id;
    private UserShortDto fromUser;
    private UserShortDto toUser;
    private LocalDateTime createdAt;
    private FriendshipStatus status;
}