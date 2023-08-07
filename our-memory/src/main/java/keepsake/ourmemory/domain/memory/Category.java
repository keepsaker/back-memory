package keepsake.ourmemory.domain.memory;

public enum Category {

    RESTAURANT("음식점"),
    CAFE("카페"),
    HOBBY("취미");

    private String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
