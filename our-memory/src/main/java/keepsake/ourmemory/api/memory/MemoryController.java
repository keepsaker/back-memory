package keepsake.ourmemory.api.memory;

import jakarta.validation.Valid;
import keepsake.ourmemory.application.memory.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequiredArgsConstructor
@RestController
public class MemoryController {

    private final MemoryService memoryService;

    @PostMapping("/memories")
    public ResponseEntity<Void> createMemory(@Valid @RequestBody MemoryCreateRequest request) {
        Long memoryId = memoryService.createMemory(request);
        return ResponseEntity.created(URI.create("/memories/" + memoryId)).build();
    }
}
