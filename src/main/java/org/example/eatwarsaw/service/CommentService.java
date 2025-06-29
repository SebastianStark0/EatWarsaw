package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CommentDto;
import org.example.eatwarsaw.exception.PlaceNotFoundException;
import org.example.eatwarsaw.mapper.CommentMapper;
import org.example.eatwarsaw.model.Comment;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.repository.CommentRepository;
import org.example.eatwarsaw.repository.PlaceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PlaceService placeService;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, PlaceService placeService, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.placeService = placeService;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getCommentsByPlaceId(Long placeId) {
        List<Comment> comments = commentRepository.findByPlaceId(placeId);
        return comments.stream()
                .map(commentMapper::toDto)
                .toList();
    }

    public CommentDto addComment(Long placeId, CommentDto dto) {
        Place place = placeService.findById(placeId);
        Comment base = commentMapper.toEntity(dto);
        Comment comment = Comment.builder()
                .text(base.getText())
                .author(base.getAuthor())
                .place(place)
                .createdAt(LocalDateTime.now())
                .build();
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }
}
