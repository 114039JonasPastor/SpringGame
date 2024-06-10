package ApiVideos.demo;

import ApiVideos.demo.controllers.PingController;
import ApiVideos.demo.controllers.PlayerController;
import ApiVideos.demo.services.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private PlayerController playerController;

    @Autowired
    private PingController pingController;

    @Autowired
    private PlayerService playerService;

    @Test
    void contextLoads() throws Exception {
        assertThat(playerController).isNotNull();
        assertThat(pingController).isNotNull();
        assertThat(playerService).isNotNull();
    }
}
