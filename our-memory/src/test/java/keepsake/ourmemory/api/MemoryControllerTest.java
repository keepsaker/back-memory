package keepsake.ourmemory.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Star;
import keepsake.ourmemory.ui.MemoryController;
import keepsake.ourmemory.ui.dto.request.MemoryCreateRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemoryController.class)
class MemoryControllerTest {

    @MockBean
    private MemoryService memoryService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 추억을_새긴다() throws Exception {
        given(memoryService.createMemory(any(), any())).willReturn(1L);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.multipart("/memories")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("title", "title")
                        .param("category", Category.CAFE.getCategoryName())
                        .param("visitedAt", LocalDateTime.now().toString())
                        .param("star", String.valueOf(Star.TWO.getValue()))
                        .param("content", "content"))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(mvcResult.getResponse().getHeader("location")).isEqualTo("/memories/1");
    }

    @Test
    void 추억의_머리말이_50자를_초과하면_예외() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/memories")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("title", "title".repeat(50))
                        .param("category", Category.CAFE.getCategoryName())
                        .param("visitedAt", LocalDateTime.now().toString())
                        .param("star", String.valueOf(Star.TWO.getValue()))
                        .param("content", "content"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 추억의_내용은_1000자를_초과하면_예외() throws Exception {
        String wrongContent = "content".repeat(1000);
        MemoryCreateRequest request = new MemoryCreateRequest("title", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), wrongContent, Collections.emptyList());
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/memories")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
