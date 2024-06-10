package ApiVideos.demo.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CredentialV2 {

    @Schema(title = "Email or UserName to logged in",
            description = "The player email or username",
            example = "email@gmail.com or username",
            nullable = false)
    @NotNull(message = "identity cant be null")
    @JsonProperty("identity")
    private String identity;
    private String password;
}
