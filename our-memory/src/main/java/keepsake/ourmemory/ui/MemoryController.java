package keepsake.ourmemory.ui;

import jakarta.validation.Valid;
import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/memories")
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;

    @PostMapping
    public ResponseEntity<Void> createMemory(@Valid @RequestBody MemoryCreateRequest request) {
        Long memoryId = memoryService.createMemory(request);
        return ResponseEntity.created(URI.create("/memories/" + memoryId)).build();
    }

    @GetMapping
    public ResponseEntity<MemoriesResponse> getMemories() {
        MemoriesResponse responses = memoryService.getMemories(1L);
        return ResponseEntity.ok(responses);
    }
}
