package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name ="matches" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @JoinColumn(name = "game_id")
    @ManyToOne
    private GameEntity game;

    @JoinColumn(name = "player1_id")
    @ManyToOne
    private PlayerEntity player1;

    @JoinColumn(name = "player2_id")
    @ManyToOne
    private PlayerEntity player2;

    @Column
    private LocalDate createdAt;
    @Column
    private LocalDate updatedAt;
    @Column
    @Enumerated(EnumType.STRING)
    private MatchStatus status;

}
