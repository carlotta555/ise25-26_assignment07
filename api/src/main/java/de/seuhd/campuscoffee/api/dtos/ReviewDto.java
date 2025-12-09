package de.seuhd.campuscoffee.api.dtos;

import lombok.Builder;
import lombok.NonNull;
import org.jspecify.annotations.Nullable;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

/**
 * DTO record for POS metadata.
 */
@Builder(toBuilder = true)
public record ReviewDto (
    @Null @Nullable Long id,
    @Null @Nullable LocalDateTime createdAt,
    @Null @Nullable LocalDateTime updatedAt,
    @NotNull Long  posId,
    @NotNull Long authorId,
    @NotBlank String review,
    @Null @Nullable Boolean approved) implements Dto<Long> {
    @Override
    public @Nullable Long getId() {
        return id;
    }
}
