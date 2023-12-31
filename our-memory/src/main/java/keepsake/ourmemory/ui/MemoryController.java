package keepsake.ourmemory.ui;

import jakarta.validation.Valid;
import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.MemoriesResponse;
import keepsake.ourmemory.ui.dto.response.SingleMemoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/memories")
@RequiredArgsConstructor
public class MemoryController {
    private final MemoryService memoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createMemory(@Valid MemoryCreateRequest request) throws IOException {
        Long memoryId = memoryService.createMemory(1L, request);
        return ResponseEntity.created(URI.create("/memories/" + memoryId)).build();
    }

    @GetMapping
    public ResponseEntity<MemoriesResponse> getMemories() {
        MemoriesResponse responses = memoryService.getMemories(1L);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{memoryId}")
    public ResponseEntity<SingleMemoryResponse> getMemory(@PathVariable Long memoryId) {
        SingleMemoryResponse response = memoryService.getMemory(1L, memoryId);
        return ResponseEntity.ok(response);
    }
}
