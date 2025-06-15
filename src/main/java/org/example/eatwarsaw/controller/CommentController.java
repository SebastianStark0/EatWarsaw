package org.example.eatwarsaw.controller;

import jakarta.validation.Valid;
import org.example.eatwarsaw.dto.CommentDto;
import org.example.eatwarsaw.model.Comment;
import org.example.eatwarsaw.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/places/{placeId}/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<CommentDto> getComments(@PathVariable Long placeId) {
        return commentService.getCommentsByPlaceId(placeId);
    }

    @PostMapping
    public CommentDto addComment(
            @PathVariable Long placeId,
            @Valid @RequestBody CommentDto dto) {
        return commentService.addComment(placeId, dto);
    }
}

