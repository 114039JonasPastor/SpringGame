package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.matchDto.MatchDto;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.Play;

import java.util.List;

public interface MatchService {
    List<Match>getMatchsByPlayer(Long playerId);

    Match createMatch(MatchDto matchDto);

    Match getMatchById(Long id);

    Play play(Long matchId, PlayRequest play);
}
