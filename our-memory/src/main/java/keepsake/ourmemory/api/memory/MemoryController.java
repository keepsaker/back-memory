package keepsake.ourmemory.api.memory;

import jakarta.validation.Valid;
import keepsake.ourmemory.api.memory.response.CategoriesResponse;
import keepsake.ourmemory.api.memory.response.CategoryResponse;
import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.application.memory.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoryController {

    private final MemoryService memoryService;

    @PostMapping("/memories")
    public ResponseEntity<Void> createMemory(@Valid @RequestBody MemoryCreateRequest request) {
        Long memoryId = memoryService.createMemory(request);
        return ResponseEntity.created(URI.create("/memories/" + memoryId)).build();
    }

    @GetMapping("/categories")
    public ResponseEntity<CategoriesResponse> findCategories() {
        List<CategoryDto> categories = memoryService.findCategories();
        CategoriesResponse response = new CategoriesResponse(
                categories.stream()
                        .map(CategoryResponse::from)
                        .toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
