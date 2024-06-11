package ar.edu.utn.frc.tup.lciii.models;

import ar.edu.utn.frc.tup.lciii.utils.validation.password.ValidPassword;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Schema(title = "Player Id", description = "The player Id", example = "1")
    private Long id;

    @Schema(title = "Player user name", description = "The player user name", example = "myUserName" , nullable = false)
    @NotNull(message = "Este campo no puede ser nulo")
    private String userName;

    @Schema(title = "Player password", description = "The player password", example = "Password1*" , nullable = false)
    @NotNull(message = "Este campo no puede ser nulo")
    @ValidPassword
    private String password;

    @Schema(title = "Player Email", description = "The player email", example = "joaquinzabala@gmail.com" , nullable = false)
    @NotNull(message = "Este campo no puede ser nulo")
    @Email(message = "El email tiene que ser valido")
    private String email;

    @Schema(title = "Player avatar url", description = "The player avatar url", example = "http://localhost:8080/avatars/myUserName" , nullable = true)
    private String avatar;

    @Schema(title = "Player last login", description = "The player last login", example = "01-01-2023 10:30:02" , nullable = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING , pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime lastLoginDate;
}
