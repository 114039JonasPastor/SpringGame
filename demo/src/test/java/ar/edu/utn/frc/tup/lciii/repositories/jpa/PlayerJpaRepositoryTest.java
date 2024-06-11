package ar.edu.utn.frc.tup.lciii.repositories.jpa;

import ar.edu.utn.frc.tup.lciii.entities.PlayerEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerJpaRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PlayerJpaRepository playerJpaRepository;
    @Test
    void findByUserNameOrEmail() {
        PlayerEntity player = new PlayerEntity();
        player.setEmail("joaquinzabala@gmail.com");
        player.setUserName("Joaquin123456");
        player.setPassword("Joaquin1*");

        entityManager.persist(player);
        entityManager.flush();

       Optional<PlayerEntity> result = playerJpaRepository.findByUserNameOrEmail(player.getUserName() , player.getEmail());

       assertEquals("Joaquin123456" , result.get().getUserName());
       assertEquals("joaquinzabala@gmail.com" , result.get().getEmail());
    }
}