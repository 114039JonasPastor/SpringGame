package ApiVideos.demo.services;

import ApiVideos.demo.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface PlayerService {
    Player getPlayerById(Long id);
    Player savePlayer(Player player);

    Player getPlayerByUserNameAndPassword(String userName, String password);
    Player getPlayerByEmailAndPassword(String email, String password);

    Player getPlayerByUserNameOrEmailAndPassword(String identity, String password);
}
