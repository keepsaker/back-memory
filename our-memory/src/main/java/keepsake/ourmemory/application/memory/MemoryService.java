package keepsake.ourmemory.application.memory;

import keepsake.ourmemory.application.memory.dto.CategoryDto;
import keepsake.ourmemory.domain.memory.Category;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MemoryService {

    public List<CategoryDto> findCategories() {

        return Arrays.stream(Category.values())
                .map(category -> new CategoryDto(category.getName()))
                .toList();
    }
}
