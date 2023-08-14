package keepsake.ourmemory.ui.tag.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TagCreateRequest {

    private Long memberId;
    private String tagName;
    private String tagColor;
}
