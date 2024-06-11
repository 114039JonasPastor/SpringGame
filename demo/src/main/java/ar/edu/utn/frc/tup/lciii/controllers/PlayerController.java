package ar.edu.utn.frc.tup.lciii.controllers;

import ar.edu.utn.frc.tup.lciii.dtos.common.ErrorApi;
import ar.edu.utn.frc.tup.lciii.models.Match;
import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.MatchService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/players")

public class PlayerController {

@Autowired
private PlayerService playerService;

@Autowired
private MatchService matchService;

    @Operation(
            summary = "Get player By id",
            description = "Return the player by id . if the player doesn't exist , return 404")

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200" , description = "Successful operation", content = @Content(
                            schema = @Schema(implementation = Player.class)
                    )),
            @ApiResponse(responseCode = "404" , description = "Not found", content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(responseCode = "500" , description = "Internal server error", content = @Content(
                            schema = @Schema(implementation = ErrorApi.class))
            )
    })


    @GetMapping("/{id}")
    public ResponseEntity<Player>getById(@PathVariable long id){

    return ResponseEntity.ok(playerService.getPlayerByid(id));
    }


    @Operation(
            summary = "Create a new player",
            description = "Return the player created with your id. If a player exists then return 404")

    @ApiResponses( value = {
            @ApiResponse(responseCode = "200" , description = "Successful operation", content = @Content(
                    schema = @Schema(implementation = Player.class)
            )),
            @ApiResponse(responseCode = "400" , description = "UserName or email already exist", content = @Content(
                    schema = @Schema(implementation = ErrorApi.class))
            ),
            @ApiResponse(responseCode = "500" , description = "Internal server error", content = @Content(
                    schema = @Schema(implementation = ErrorApi.class))
            )
    })
    @PostMapping()
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player){

        Player newPlayer = playerService.savePlayer(player);

        if(newPlayer == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST , "El usuario o el email , ya existen");
        }

        return ResponseEntity.ok(newPlayer);
    }

    @GetMapping("/{id}/matches")
    public ResponseEntity<List<Match>>getMatchesOfPlayer(@PathVariable Long id){
        List<Match>matches = matchService.getMatchsByPlayer(id);
        return ResponseEntity.ok(matches);
    }

}
