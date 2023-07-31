package keepsake.ourmemory.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import keepsake.ourmemory.api.memory.MemoryController;
import keepsake.ourmemory.api.memory.MemoryCreateRequest;
import keepsake.ourmemory.application.memory.MemoryService;
import keepsake.ourmemory.domain.memory.Category;
import keepsake.ourmemory.domain.memory.Star;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

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
        MemoryCreateRequest request = new MemoryCreateRequest("title", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content", List.of("image"));
        String jsonRequest = objectMapper.writeValueAsString(request);

        given(memoryService.createMemory(any()))
                .willReturn(1L);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/memories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(mvcResult.getResponse().getHeader("location")).isEqualTo("/memories/1");
    }

    @Test
    void 추억의_머리말이_50자를_초과하면_예외() throws Exception {
        String wrongTitle = "title".repeat(50);
        MemoryCreateRequest request = new MemoryCreateRequest(wrongTitle, Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), "content", List.of("image"));
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/memories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 추억의_내용은_1000자를_초과하면_예외() throws Exception {
        String wrongContent = "content".repeat(1000);
        MemoryCreateRequest request = new MemoryCreateRequest("title", Category.CAFE.getCategoryName(), LocalDateTime.now(), Star.TWO.getValue(), wrongContent, List.of("image"));
        String jsonRequest = objectMapper.writeValueAsString(request);

        mockMvc.perform(MockMvcRequestBuilders.post("/memories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }
}
