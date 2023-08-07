package keepsake.ourmemory.ui;

import keepsake.ourmemory.application.MemoryService;
import keepsake.ourmemory.ui.dto.MemoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memories")
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;

    @GetMapping
    public ResponseEntity<MemoriesResponse> getMemories() {
        MemoriesResponse responses = memoryService.getMemories(1L);
        return ResponseEntity.ok(responses);
    }
}
