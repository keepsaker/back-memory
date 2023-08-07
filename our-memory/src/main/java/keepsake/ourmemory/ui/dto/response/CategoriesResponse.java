package keepsake.ourmemory.ui.dto.response;

import java.util.List;

public class CategoriesResponse {

    private List<CategoryResponse> categories;

    protected CategoriesResponse() {
    }

    public CategoriesResponse(List<CategoryResponse> categories) {
        this.categories = categories;
    }

    public List<CategoryResponse> getCategories() {
        return categories;
    }
}
