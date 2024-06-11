package ar.edu.utn.frc.tup.lciii.dtos.play;

import ar.edu.utn.frc.tup.lciii.models.rps.ShapeHand;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlayRpsDto implements PlayRequest {

    @NotNull
    @JsonTypeInfo("shape_hand_player_1")
    private ShapeHand shapeHandPlayer1;

    @JsonTypeInfo("shape_hand_player_2")
    private ShapeHand shapeHandPlayer2;

}
