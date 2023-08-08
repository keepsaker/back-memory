package keepsake.ourmemory.ui.tag;

import keepsake.ourmemory.application.tag.TagService;
import keepsake.ourmemory.ui.tag.dto.TagCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {

    private final TagService tagService;

    @PostMapping("/tags")
    public ResponseEntity<Void> createTag(@RequestBody TagCreateRequest request) {
        tagService.createTag(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
