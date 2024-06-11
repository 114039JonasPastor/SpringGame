package ApiVideos.demo.models;

import ApiVideos.demo.utils.validation.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Schema(title = "Player ID", description = "The player id", example = "1")
    private Long id;

    @Schema(title = "Player user name", description = "The player user name", example = "myUserName", nullable = false)
    @NotNull(message = "UserName can't be null")
    private String userName;

    @Schema(title = "Player password", description = "The player password", example = "Password03#", nullable = false)
    @NotNull(message = "Password can't be null")
    @ValidPassword
    private String password;

    @Schema(title = "Player email", description = "The player email", example = "email@gmail.com", nullable = false)
    @NotNull(message = "Email can't be null")
    @Email(message = "The email needs to be a valid email")
    private String email;

    @Schema(title = "Player avatar url", description = "The player avatar", example = "https://localhost:8080/avatars/myUserName", nullable = true)
    private String avatar;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")

    @Schema(title = "Player last login", description = "The player last login", example = "01-01-2020 21:10:50", nullable = true)
    private LocalDateTime lastLoginDate;

}
