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
    private final PlaceRepository placeRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, PlaceRepository placeRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.placeRepository = placeRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDto> getCommentsByPlaceId(Long placeId) {
        List<Comment> comments = commentRepository.findByPlaceId(placeId);
        return comments.stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDto addComment(Long placeId, CommentDto dto) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceNotFoundException(placeId));

        Comment comment = commentMapper.toEntity(dto);
        comment.setPlace(place);
        comment.setCreatedAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        return commentMapper.toDto(savedComment);
    }
}
