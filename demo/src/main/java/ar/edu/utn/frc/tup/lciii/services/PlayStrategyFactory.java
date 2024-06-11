package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.rps.MatchRps;
import ar.edu.utn.frc.tup.lciii.models.rps.PlaysRps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlayStrategyFactory {

    @Autowired
    private PlayMatch<PlaysRps, MatchRps> playMatchRps;

    public PlayMatch getPlayerStrategy(String gameCode){
        switch (gameCode){
            case "RPS":
                return playMatchRps;
            default:
                return playMatchRps;
        }
    }
}
