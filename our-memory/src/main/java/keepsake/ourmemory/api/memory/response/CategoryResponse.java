package keepsake.ourmemory.api.memory.response;

import keepsake.ourmemory.application.memory.dto.CategoryDto;

public class CategoryResponse {

    private String name;

    public CategoryResponse() {
    }

    public CategoryResponse(String name) {
        this.name = name;
    }

    public static CategoryResponse from(CategoryDto categoryDto) {
        return new CategoryResponse(categoryDto.getName());
    }

    public String getName() {
        return name;
    }
}
