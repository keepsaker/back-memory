package keepsake.ourmemory.domain.memory;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Category {
    RESTAURANT("restaurant"), CAFE("cafe"), HOBBY("hobby");

    private final String categoryName;

    Category(String categoryName) {
        this.categoryName = categoryName;
    }

    private static final Map<String, Category> categories =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Category::getCategoryName, Function.identity())));

    public static Category from(String categoryName) {
        if (categories.containsKey(categoryName)) {
            return categories.get(categoryName);
        }
        throw new IllegalArgumentException("존재하지 않는 카테고리 입니다.");
    }

    public String getCategoryName() {
        return categoryName;
}
