package keepsake.ourmemory.ui;

import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.application.memory.dto.CategoryDto;
import keepsake.ourmemory.ui.dto.response.CategoriesResponse;
import keepsake.ourmemory.ui.dto.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final MemoryService memoryService;

    @GetMapping
    public ResponseEntity<CategoriesResponse> findCategories() {
        List<CategoryDto> categories = memoryService.findCategories();
        CategoriesResponse response = new CategoriesResponse(
                categories.stream()
                        .map(CategoryResponse::from)
                        .toList());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
