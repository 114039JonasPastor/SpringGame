package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.matchDto.MatchDto;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.entities.MatchEntity;
import ar.edu.utn.frc.tup.lciii.models.*;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchEntityFactory;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.MatchJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.action.internal.EntityActionVetoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchJpaRepository matchJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private GameService gameService;

    @Autowired
    private PlayStrategyFactory playStrategyFactory;

    @Override
    public List<Match> getMatchsByPlayer(Long playerId) {
        List<Match> matches = new ArrayList<>();

        Optional<List<MatchEntity>> optionalMatchEntities = matchJpaRepository.getAllByPlayerId(playerId);

        if (optionalMatchEntities.isPresent()) {
            optionalMatchEntities.get().forEach(
                    me -> {matches.add(modelMapper.map(me, MatchFactory.getTypeOfMatch(me.getGame().getCode())));}
            );
        }
        return matches;
    }

    @Override
    public Match createMatch(MatchDto matchDto) {
        Player player = playerService.getPlayerByid(matchDto.getPlayerId());
        Game game = gameService.getGame(matchDto.getGameId());

        Match match = MatchFactory.createMatch(player, game);
        MatchEntity matchEntity = matchJpaRepository.save(modelMapper.map(match, MatchEntityFactory.getTypeOfMatch(match)));

        return modelMapper.map(matchEntity, Match.class);
    }

    @Override
    public Match getMatchById(Long id) {
        //MatchEntity me = matchJpaRepository.getReferenceById(id);
        MatchEntity me = (MatchEntity) Hibernate.unproxy(matchJpaRepository.getReferenceById(id));
        //MatchEntity me = matchJpaRepository.getMatchById(id);

        if(me != null){
            Match match = modelMapper.map(me, MatchFactory.getTypeOfMatch(me.getGame().getCode()));
            return match;
        } else{
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @Override
    public Play play(Long matchId, PlayRequest play) {
        Match match = this.getMatchById(matchId);
        if(match == null){
            throw new EntityNotFoundException();
        }
        Play play = PlayFactory.getPlayInstance(playRequest, match.getGame().getCode());
        PlayMatch playMatch = playStrategyFactory.getPlayerStrategy(match.getGame().getCode());
        return playMatch.play(play, match);
    }
}

