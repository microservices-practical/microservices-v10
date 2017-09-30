package microservices.book.gamification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import microservices.book.gamification.domain.Badge;
import microservices.book.gamification.domain.GameStats;
import microservices.book.gamification.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * @author moises.macero
 */
@RunWith(SpringRunner.class)
@WebMvcTest(UserStatsController.class)
public class UserStatsControllerTest {

    @MockBean
    private GameService gameService;

    @Autowired
    private MockMvc mvc;

    private JacksonTester<GameStats> json;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getUserStatsTest() throws Exception{
        // given
        GameStats gameStats = new GameStats(1L, 2000, Collections.singletonList(Badge.GOLD_MULTIPLICATOR));
        given(gameService.retrieveStatsForUser(1L)).willReturn(gameStats);

        // when
        MockHttpServletResponse response = mvc.perform(
                get("/stats?userId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString())
                .isEqualTo(json.write(gameStats).getJson());
    }
}