package org.example.eatwarsaw.mapper;

import org.example.eatwarsaw.dto.CommentDto;
import org.example.eatwarsaw.model.Comment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    Comment toEntity(CommentDto dto);

    CommentDto toDto(Comment comment);

    List<CommentDto> toDtoList(List<Comment> comments);
}