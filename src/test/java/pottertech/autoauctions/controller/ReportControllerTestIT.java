package pottertech.autoauctions.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import pottertech.autoauctions.service.implementation.ReportServiceImpl;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = ReportController.class)
@ActiveProfiles("h2")
class ReportControllerTestIT {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ReportServiceImpl reportService;

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void getAllReportsShort() throws Exception {
        when(reportService.getAllReportsShort(any())).thenReturn(any());

        mockMvc.perform(get("/")
                        .param("page", "1")
                        .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void addReport() throws Exception {
        mockMvc.perform(get("/report/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-report"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void testAddReport() throws Exception {
        mockMvc.perform(post("/report/add")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/report/add?success"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void getFilteredReports() throws Exception {
        when(reportService.getAllReportsShort(any())).thenReturn(any());

        mockMvc.perform(get("/")
                        .param("page", "1")
                        .param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void approveReport() throws Exception {
        mockMvc.perform(get("/approve")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN"})
    void buyCar() throws Exception {
        mockMvc.perform(get("/buy")
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}