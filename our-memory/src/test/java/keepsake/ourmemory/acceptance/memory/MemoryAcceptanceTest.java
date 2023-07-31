package keepsake.ourmemory.acceptance.memory;

import io.restassured.RestAssured;
import keepsake.ourmemory.api.memory.response.CategoryResponse;
import keepsake.ourmemory.domain.memory.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemoryAcceptanceTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }

    @Test
    void 카테고리_목록을_조회한다() {
        // given
        List<String> expected = Arrays.stream(Category.values())
                .map(Category::getName)
                .toList();

        // when
        List<CategoryResponse> response = given()
                .contentType(JSON)
                .when()
                .get("/categories")
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("categories", CategoryResponse.class);

        List<String> names = response.stream()
                .map(CategoryResponse::getName)
                .toList();

        // then
        assertThat(names).containsExactlyInAnyOrderElementsOf(expected);
    }
}
