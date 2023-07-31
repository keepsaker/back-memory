package keepsake.ourmemory.api.memory;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class MemoryCreateRequest {

    private String title;
    private String category;
    // tags는 다음 스프린트라 냅둘게요
    private LocalDateTime visitedAt;
    private int star;
    private String content;
    private List<String> images;
}
