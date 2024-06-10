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
public class EmailIdentity extends Identity {

    @Schema(title = "Email to logged in",
            description = "The player email",
            example = "email@gmail.com",
            nullable = false)
    @NotNull(message = "email cant be null")
    @JsonProperty("email")
    private String email;
}
