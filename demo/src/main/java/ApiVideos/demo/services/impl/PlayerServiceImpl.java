package ApiVideos.demo.services.impl;

import ApiVideos.demo.configs.MappersConfig;
import ApiVideos.demo.entities.PlayerEntity;
import ApiVideos.demo.models.Player;
import ApiVideos.demo.repositories.jpa.PlayerJpaRepository;
import ApiVideos.demo.services.PlayerService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class PlayerServiceImpl  implements PlayerService {

    @Autowired
    private PlayerJpaRepository playerJpaRepository;


    @Qualifier("modelMaper")
    @Autowired
    private ModelMapper mapper;

    /*@Autowired
    private MatchService matchService;*/
    @Override
    public Player getPlayerByid(Long id) {
        PlayerEntity playerEntity = playerJpaRepository.getReferenceById(id);

        if(Objects.isNull(playerEntity.getUserName())){

            throw new EntityNotFoundException();
        }
        Player player = mapper.map(playerEntity, Player.class);

        return player;
    }

    @Override
    public Player savePlayer(Player player) {
        Optional<PlayerEntity>playerEntityOptional =  playerJpaRepository.findByUserNameOrEmail(
                player.getUserName(), player.getEmail());

        if(playerEntityOptional.isEmpty()){

            PlayerEntity playerEntity = mapper.map(player , PlayerEntity.class); //convertimos el obj por parametro , en tipo entity
            PlayerEntity playerEntitySaved = playerJpaRepository.save(playerEntity); // guaradamos el player en la bd

            return mapper.map(playerEntitySaved, Player.class); // convierto el playerEntity en player.
        }
        return null;
    }

    @Override
    public Player getPlayerByUserNameAndPassword(String username, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameAndPassword(username , password);

        if(playerEntityOptional.isPresent()){
            return mapper.map(playerEntityOptional.get() , Player.class);
        }else{
            throw new EntityNotFoundException("Username o password invalid");
        }
    }

    @Override
    public Player getPlayerByEmailAndPassword(String email, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByEmailAndPassword(email , password);

        if(playerEntityOptional.isPresent()){
            return mapper.map(playerEntityOptional.get() , Player.class);
        }else{
            throw new EntityNotFoundException("Email o password invalid");
        }
    }

    @Override
    public Player getPlayerByUserNameOrEmailAndPassword(String identity, String password) {
        Optional<PlayerEntity> playerEntityOptional = playerJpaRepository.findByUserNameOrEmailAndPassword(identity,password);

        if(playerEntityOptional.isPresent()){
            return mapper.map(playerEntityOptional.get(),Player.class);
        }else{
            throw new EntityNotFoundException("Some parameters are incorrected");
        }
    }

}
