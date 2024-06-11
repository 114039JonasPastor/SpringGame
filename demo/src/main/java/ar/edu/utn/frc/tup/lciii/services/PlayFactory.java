package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRequest;
import ar.edu.utn.frc.tup.lciii.dtos.play.PlayRpsDto;
import ar.edu.utn.frc.tup.lciii.models.Play;
import ar.edu.utn.frc.tup.lciii.models.rps.PlaysRps;

public class PlayFactory {
    public static Play getPlayInstance(PlayRequest playRequest, String gameCode){
        switch (gameCode){
            case "RPS":
                return getPlayInstance(playRequest);
            default:
                return getPlayInstance(playRequest);
        }
    }

    private static Play getPlayRpsInstance(PlayRequest playRequest){
        PlayRpsDto playRpsDto = (PlayRpsDto) playRequest;
        PlaysRps playsRps = new PlaysRps();
        playsRps.setShapeHandPlayer1(playRpsDto.getShapeHandPlayer1());
        playsRps.setShapeHandPlayer2(playRpsDto.getShapeHandPlayer2());
        return playsRps;
    }
}
