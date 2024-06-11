package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.GameEntity;
import ar.edu.utn.frc.tup.lciii.models.Game;
import ar.edu.utn.frc.tup.lciii.repositories.jpa.GameJpaRepository;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameJpaRepository gameJpaRepository;

    @Autowired
    ModelMapper modelMapper;
    @Override
    public Game getGame(Long idGame) {
        GameEntity gameEntity = gameJpaRepository.getReferenceById(idGame);
        return modelMapper.map(gameEntity , Game.class);
    }
}
