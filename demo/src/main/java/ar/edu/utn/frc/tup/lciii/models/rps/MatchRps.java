package ar.edu.utn.frc.tup.lciii.models.rps;

import ar.edu.utn.frc.tup.lciii.models.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchRps  extends Match {
    private Integer numberOfPlays;
    private Integer remaiderPlays;
    private Integer player1Score;
    private Integer player2Score;
    private List<PlaysRps> plays;
    private Long winneId;
}
