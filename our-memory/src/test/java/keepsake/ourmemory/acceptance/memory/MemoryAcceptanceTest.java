package keepsake.ourmemory.acceptance.memory;

import io.restassured.RestAssured;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import keepsake.ourmemory.ui.dto.response.CategoryResponse;
import keepsake.ourmemory.ui.dto.response.SingleMemoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

// TODO: 나중에 데이터 트런케이트하는 방법 확정나면 풀 것
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Disabled
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
                .map(Category::getCategoryName)
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

    @Test
    void 하나의_추억을_조회한다() {
        // given
        MemoryCreateRequest request = new MemoryCreateRequest("title1", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content1", Collections.emptyList());
        Long memoryId = 추억을_추가한다(request);

        // when
        SingleMemoryResponse response = given()
                .contentType(JSON)
                .when()
                .get("/memories/" + memoryId)
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getObject(".", SingleMemoryResponse.class);

        // then
        assertAll(
                () -> assertThat(response.star()).isEqualTo(request.getStar())

        );
    }

    @Test
    private Long 추억을_추가한다(MemoryCreateRequest memoryCreateRequest) {
        return Long.valueOf(
                given()
                        .contentType(JSON)
                        .when()
                        .body(memoryCreateRequest)
                        .post("/memories")
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .header("Location")
                        .split("/")[2]);
    }
}
