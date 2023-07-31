package keepsake.ourmemory.api.memory;

import keepsake.ourmemory.api.memory.response.CategoriesResponse;
import keepsake.ourmemory.api.memory.response.CategoryResponse;
import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.application.memory.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoryController {

    public final MemoryService memoryService;

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
