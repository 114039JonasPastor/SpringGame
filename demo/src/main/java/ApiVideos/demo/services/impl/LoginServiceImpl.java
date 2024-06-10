package ApiVideos.demo.services.impl;

import ApiVideos.demo.dtos.login.Credential;
import ApiVideos.demo.dtos.login.CredentialV2;
import ApiVideos.demo.dtos.login.EmailIdentity;
import ApiVideos.demo.dtos.login.UserNameIdentity;
import ApiVideos.demo.models.Player;
import ApiVideos.demo.services.LoginService;
import ApiVideos.demo.services.PlayerService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private PlayerService playerService;

    @Override
    public Player login(Credential credential) {
        if(credential.getIdentity() instanceof UserNameIdentity){
            return loginWithIdentity((UserNameIdentity) credential.getIdentity(), credential.getPassword());
        } else{
            return loginWithIdentity((EmailIdentity) credential.getIdentity(), credential.getPassword());
        }
    }

    @Override
    public Player login(CredentialV2 credential) {
        return playerService.getPlayerByUserNameOrEmailAndPassword(credential.getIdentity(), credential.getPassword());
    }

    private Player loginWithIdentity(UserNameIdentity userNameIdentity, String password) {
        return playerService.getPlayerByUserNameAndPassword(userNameIdentity.getUserName(), password);
    }

    private Player loginWithIdentity(EmailIdentity emailIdentity, String password) {
        return playerService.getPlayerByEmailAndPassword(emailIdentity.getEmail(), password);
    }
}
