package keepsake.ourmemory.ui.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class MemoryCreateRequest {

    @Length(max = 50)
    private String title;

    private String category;
    // tags는 다음 스프린트라 냅둘게요

    private LocalDateTime visitedAt;

    private int star;

    @Length(max = 1000)
    private String content;

    private List<String> images;
}
