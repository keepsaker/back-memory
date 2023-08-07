package keepsake.ourmemory.ui.dto.response;

import keepsake.ourmemory.domain.memory.Memory;

import java.time.LocalDateTime;
import java.util.List;

public record MemoryResponse(String title,
                             String category,
                             List<TagResponse> tags,
                             LocalDateTime visitedAt,
                             int star,
                             LocationResponse location) {
    public static MemoryResponse from(final Memory memory) {
        return new MemoryResponse(
                memory.getTitleValue(),
                memory.getCategoryValue(),
                memory.getMemoryTags()
                        .stream()
                        .map(TagResponse::from)
                        .toList(),
                memory.getVisitedAt(),
                memory.getStarValue(),
                // TODO : image 추가
                new LocationResponse(
                        memory.getLatitudeValue(),
                        memory.getLongitudeValue()
                )
        );
    }
}
