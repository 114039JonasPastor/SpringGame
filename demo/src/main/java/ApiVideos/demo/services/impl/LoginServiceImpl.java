package ApiVideos.demo.services.impl;

import ApiVideos.demo.dtos.login.Credential;
import ApiVideos.demo.dtos.login.CredentialV2;
import ApiVideos.demo.dtos.login.EmailIdentity;
import ApiVideos.demo.dtos.login.UserNameIdentity;
import ApiVideos.demo.models.Player;
import ApiVideos.demo.services.LoginService;
import ApiVideos.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PlayerService playerService;
    @Override
    public Player login(Credential credential) {
        if(credential.getIdentity() instanceof UserNameIdentity){
            return loginWithIdentity((UserNameIdentity)credential.getIdentity() , credential.getPassword());
        }else{
            return loginWithIdentity((EmailIdentity)credential.getIdentity() , credential.getPassword());
        }
    }

    @Override
    public Player login(CredentialV2 credentialV2) {
        Player player = playerService.getPlayerByUserNameOrEmailAndPassword(credentialV2.getIdentity() , credentialV2.getPassword());
        return updateLastLogin(player);
    }


    private Player loginWithIdentity(UserNameIdentity userNameIdentity , String password){
        Player player = playerService.getPlayerByUserNameAndPassword(userNameIdentity.getUserName() , password);
        return updateLastLogin(player);
    }

    private Player loginWithIdentity(EmailIdentity emailIdentity , String password){
        Player player = playerService.getPlayerByEmailAndPassword(emailIdentity.getEmail() , password);
        return updateLastLogin(player);
    }

    private Player updateLastLogin(Player player){
        player.setLastLoginDate(LocalDateTime.now());
        return playerService.savePlayer(player);
    }
}
