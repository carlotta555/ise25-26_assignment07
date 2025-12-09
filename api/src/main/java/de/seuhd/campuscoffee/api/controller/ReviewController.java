package de.seuhd.campuscoffee.api.controller;

import de.seuhd.campuscoffee.api.dtos.ReviewDto;
import de.seuhd.campuscoffee.api.mapper.DtoMapper;
import de.seuhd.campuscoffee.api.openapi.CrudOperation;
import de.seuhd.campuscoffee.domain.model.objects.Review;
import de.seuhd.campuscoffee.domain.ports.api.CrudService;
import de.seuhd.campuscoffee.domain.ports.api.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static de.seuhd.campuscoffee.api.openapi.Operation.*;
import static de.seuhd.campuscoffee.api.openapi.Resource.REVIEW;

/**
 * Controller for handling reviews for POS, authored by users.
 */
@Tag(name="Reviews", description="Operations for managing reviews for points of sale.")
@Controller
@RequestMapping("/api/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController extends CrudController<Review, ReviewDto, Long> {


    private final ReviewService reviewService;
    private final DtoMapper<Review, ReviewDto> reviewMapper;

    @Override
    protected @NonNull CrudService<Review, Long> service() {
        return reviewService;
    }

    @Override
    protected @NonNull DtoMapper<Review, ReviewDto> mapper() {
        return reviewMapper;
    }

    @Operation
    @CrudOperation(operation=GET_ALL, resource=REVIEW)
    @GetMapping("")

    public @NonNull ResponseEntity<List<ReviewDto>> getAll() {return super.getAll();}
    public @NonNull ResponseEntity <ReviewDto> getById (@PathVariable Long id) {return super.getById(id);}
    public @NonNull ResponseEntity < ReviewDto > create (@PathVariable ReviewDto dto) {return super.create(dto);}
    public @NonNull ResponseEntity < ReviewDto > update (@PathVariable Long id, @PathVariable ReviewDto dto) {return super.update(id,dto);}
    public @NonNull ResponseEntity <Void > delete (@PathVariable Long id) {return super.delete(id);}
    public ResponseEntity <List<ReviewDto>> filter(@RequestParam(" pos_id ") Long posId, @RequestParam(" approved ") Boolean approved)
    {
        List<Review> reviews = reviewService.filter(posId, approved);
        List<ReviewDto> result = reviews.stream().map(reviewMapper::fromDomain).toList();
        return ResponseEntity.ok(result);
    }
    public ResponseEntity <ReviewDto> approve (@PathVariable Long id , @RequestParam (" user_id ") Long userId)
      {
          Review review = reviewService.getById(id);
          Review approved = reviewService.approve(review, userId);
          ReviewDto dto = reviewMapper.fromDomain(approved);
          return ResponseEntity.ok(dto);
      }

}
