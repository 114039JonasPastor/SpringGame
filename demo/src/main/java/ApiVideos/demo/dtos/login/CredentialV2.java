package ApiVideos.demo.dtos.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialV2 {
    @Schema(title = "Email or userName to logged in",
            description = "The player email or username",
            example = " user@gmail.com or username" ,
            nullable = false)
    @NotNull(message = "identity cant'be null")
    @JsonProperty("identity")
    private String identity;
    @NotNull(message = "password cant'be null")
    private String password;
}
