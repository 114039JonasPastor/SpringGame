package ApiVideos.demo.services;

import ApiVideos.demo.dtos.login.Credential;
import ApiVideos.demo.dtos.login.CredentialV2;
import ApiVideos.demo.models.Player;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Player login(Credential credential);
    Player login(CredentialV2 credential);
}
