package ApiVideos.demo.repositories.jpa;

import ApiVideos.demo.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerJpaRepository extends JpaRepository<PlayerEntity, Long> {
    Optional<PlayerEntity>findByUserNameOrEmail(String userName , String email);


    Optional<PlayerEntity> findByUserNameAndPassword(String username , String password);


    Optional<PlayerEntity> findByEmailAndPassword(String email , String password);


    @Query("SELECT p FROM PlayerEntity p"+
            " WHERE (p.userName Like :identity or p.email Like :identity) " +
            "AND p.password Like :password")
    Optional<PlayerEntity>findByUserNameOrEmailAndPassword(@Param("identity") String identity , @Param("password") String password );
}
