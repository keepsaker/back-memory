package keepsake.ourmemory.ui.tag;

import com.fasterxml.jackson.databind.ObjectMapper;
import keepsake.ourmemory.application.tag.TagService;
import keepsake.ourmemory.ui.tag.dto.TagCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TagController.class)
class TagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TagService tagService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 태그_생성_후_201_을_반환한다() throws Exception {
        TagCreateRequest request = new TagCreateRequest(1L, "tagName", "tagColor");
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/tags")
                        .content(jsonRequest)
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
