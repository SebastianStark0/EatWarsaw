package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.CommentDto;
import org.example.eatwarsaw.model.Comment;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;

    @Autowired
    private PlaceRepository placeRepository;

    @Test
    public void testAddAndGetComments() {
//        Place place = placeRepository.save(new Place());
//
//        CommentDto dto = new CommentDto();
//        dto.setAuthor("TestUser");
//        dto.setText("Great place!");
//        dto.setRating(5);
//
//        Comment comment = commentService.addComment(place.getId(), dto);
//        assertNotNull(comment.getId());
//
//        List<Comment> comments = commentService.getCommentsByPlaceId(place.getId());
//        assertFalse(comments.isEmpty());
//        assertEquals("TestUser", comments.get(0).getAuthor());
    }
}
