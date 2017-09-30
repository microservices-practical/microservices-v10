package microservices.book.gamification.controller;

import microservices.book.gamification.service.AdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * @author moises.macero
 */
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test")
@WebMvcTest(AdminController.class)
public class AdminControllerEnabledTest {

    @MockBean
    private AdminService adminService;

    @Autowired
    private MockMvc mvc;

    /**
     * This test checks that the controller is working as expected when
     * the profile is set to test (see annotation in class declaration)
     * @throws Exception if any error occurs
     */
    @Test
    public void deleteDatabaseTest() throws Exception {
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/gamification/admin/delete-db")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        verify(adminService).deleteDatabaseContents();
    }
}