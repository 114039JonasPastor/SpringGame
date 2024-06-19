package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.entities.PlayRpsEntity;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.MatchStatus;
import ar.edu.utn.frc.tup.lciii.models.Play;
import ar.edu.utn.frc.tup.lciii.models.rps.MatchRps;
import ar.edu.utn.frc.tup.lciii.models.rps.PlaysRps;
import ar.edu.utn.frc.tup.lciii.models.rps.ShapeHand;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.PlayRpsJparepository;
import ar.edu.utn.frc.tup.lciii.services.PlayMatch;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

@Service
public class PlayMatchRpsImpl implements PlayMatch<PlaysRps, MatchRps> {
    @Autowired
    private PlayRpsJparepository rpsJparepository;

    private Random random = new Random();
    @Autowired
    private PlayRpsJparepository playRpsJparepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Override
    public PlaysRps play(PlaysRps playRps, MatchRps matchRps) {
        playRps.setMatchRpsId(matchRps.getId());
        if(Objects.isNull(playRps.getShapeHandPlayer2())){
            playRps.setShapeHandPlayer2(getRandomShapeHand());
        }
        evaluatePlay(playRps, matchRps);
        calculateMatchScore(playRps, matchRps);
        calculateMatchStatus(matchRps);
        matchRps.setUpdatedAt(LocalDateTime.now());
        PlayRpsEntity playRpsEntity = modelMapper.map(playRps, PlayRpsEntity.class);
        playRpsJparepository.save(playRpsEntity);
        MatchEntity matchEntity = modelMapper.map(matchRps, MatchEntity.class);
        matchJpaRepository.save(matchEntity);
        return playRps;
    }

    private ShapeHand getRandomShapeHand(){
        Integer randomIndex = random.nextInt(3);
        return ShapeHand.values()[randomIndex];
    }

    private void evaluatePlay(PlaysRps playRps, MatchRps matchRps){
        if (!isPlayTie(playRps)){
            setWinner(playRps, matchRps);
        }
    }

    private Boolean isPlayTie(PlaysRps playsRps){
        return playsRps.getShapeHandPlayer1().equals(playsRps.getShapeHandPlayer2());
    }

    private void setWinner(PlaysRps playRps, MatchRps matchRps){
        if(playRps.getShapeHandPlayer1().equals(ShapeHand.PAPER)){
            if(playRps.getShapeHandPlayer2().equals(ShapeHand.ROCK)){
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else {
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        } else if (playRps.getShapeHandPlayer1().equals(ShapeHand.ROCK)) {
            if (playRps.getShapeHandPlayer2().equals(ShapeHand.SCISSORS)){
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else{
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        } else {
            if(playRps.getShapeHandPlayer2().equals(ShapeHand.PAPER)){
                playRps.setWinnerId(matchRps.getPlayer1().getId());
            } else{
                playRps.setWinnerId(matchRps.getPlayer2().getId());
            }
        }
    }

    private void calculateMatchScore(PlaysRps playRps, MatchRps matchRps){
        if(Objects.nonNull(playRps.getWinnerId())){
            if(playRps.getWinnerId().equals(matchRps.getPlayer1().getId())){
                matchRps.setPlayer1Score(matchRps.getPlayer1Score() + 1);
            } else{
                matchRps.setPlayer2Score(matchRps.getPlayer2Score() + 1);
            }
        }
    }

    private void calculateMatchStatus(MatchRps matchRps){
        matchRps.setRemaiderPlays(matchRps.getRemaiderPlays() - 1);
        if(matchRps.getRemaiderPlays() == 0){
            matchRps.setStatus(MatchStatus.FINISHED);
            if(!isMatchTie(matchRps)){
                if(matchRps.getPlayer1Score() > matchRps.getPlayer2Score()){
                    matchRps.setWinneId(matchRps.getPlayer1().getId());
                } else {
                    matchRps.setWinneId(matchRps.getPlayer2().getId());
                }
            }
        }
    }

    private Boolean isMatchTie(MatchRps matchRps){
        return matchRps.getPlayer1Score().equals(matchRps.getPlayer2Score());
    }
}
