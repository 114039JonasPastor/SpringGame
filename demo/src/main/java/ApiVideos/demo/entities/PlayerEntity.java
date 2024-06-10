package ApiVideos.demo.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String userName;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String avatar;

    @Column
    private LocalDateTime lastLogin;

    @Column
    private  LocalDateTime createdAt;

    @Column
    private LocalDateTime updatedAt;
}
