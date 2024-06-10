package ApiVideos.demo.repositories.jpa;

import ApiVideos.demo.entities.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PlayerJpaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;

    @Test
    public void findByUserNameOrEmailTest(){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setEmail("email@gmail.com");
        playerEntity.setUserName("jPastor");
        playerEntity.setPassword("Password03#");

        entityManager.persist(playerEntity);
        entityManager.flush();

        Optional<PlayerEntity> result = playerJpaRepository.findByUserNameOrEmail("jPastor","email@gmail.com");
        assertEquals("jPastor", result.get().getUserName());
    }
}
