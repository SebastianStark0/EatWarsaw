package org.example.eatwarsaw.service;

import org.example.eatwarsaw.dto.PlaceDto;
import org.example.eatwarsaw.model.Category;
import org.example.eatwarsaw.model.Place;
import org.example.eatwarsaw.repository.CategoryRepository;
import org.example.eatwarsaw.repository.PlaceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaceServiceTest {

    @Mock
    private PlaceRepository placeRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private PlaceService placeService;
//
//    @Test
//    void getById_existingPlace_returnsPlace() {
//        Place place = new Place();
//        place.setId(1L);
//        place.setName("Test Place");
//
//        when(placeRepository.findById(1L)).thenReturn(Optional.of(place));
//
//        Place result = placeService.getById(1L);
//
//        assertNotNull(result);
//        assertEquals("Test Place", result.getName());
//        verify(placeRepository).findById(1L);
//    }
//
//    @Test
//    void getById_notFound_throwsException() {
//        when(placeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(RuntimeException.class, () -> placeService.getById(42L));
//    }
//
//    @Test
//    void createPlace_validDto_savesPlace() {
//        PlaceDto dto = new PlaceDto();
//        dto.setName("New Place");
//        dto.setAddress("Address");
//        dto.setImageUrl("http://img.url");
//        dto.setCategoryIds(Set.of(1L));
//
//        Category category = new Category();
//        category.setId(1L);
//        category.setName("Bar");
//
//        when(placeRepository.save(any(Place.class))).thenAnswer(invocation -> invocation.getArgument(0));
//
//        Place saved = placeService.createPlace(dto);
//
//        assertEquals("New Place", saved.getName());
//        assertEquals(1, saved.getCategories().size());
//        verify(placeRepository).save(any(Place.class));
//    }
}
