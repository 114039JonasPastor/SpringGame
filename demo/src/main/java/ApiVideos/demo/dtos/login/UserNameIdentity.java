package ApiVideos.demo.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserNameIdentity extends Identity{

    @NotNull(message = "user_name cant be null")
    @JsonProperty("user_name")
    private String userName;
}
