package keepsake.ourmemory.ui.dto;

import java.time.LocalDateTime;
import java.util.List;

public record MemoryResponse(String title,
                             String category,
                             List<TagResponse> tags,
                             LocalDateTime visitedAt,
                             int star,
                             LocationResponse location) {

}
