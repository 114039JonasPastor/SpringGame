package ar.edu.utn.frc.tup.lciii.controllers;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import ar.edu.utn.frc.tup.lciii.models.Player;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

//todo = prueba con MockBean
@WebMvcTest(PlayerController.class)

public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PlayerService playerService;

    @Test
    public void getByidTest() throws Exception {
        /*when(service.greet()).thenReturn("Hello, Mock");
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, Mock")));*/

        Player player =
                new Player(1L , "joaquin","Joaquin1*","joaquin@gmail.com" , null , null);

        when(playerService.getPlayerByid(1L)).thenReturn(player);
        this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("joaquin"))
                .andExpect(jsonPath("$.email").value("joaquin@gmail.com"))
                .andExpect(jsonPath("$.password").value("Joaquin1*"))
        ;


        //esta es otra forma de hacerlo
       /* MvcResult mvcResult = this.mockMvc.perform(get("/players/1")).andDo(print()).andExpect(status().isOk())
                .andReturn();

        Player playerResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString() , Player.class);

        Assertions.assertEquals("joaquin" , playerResult.getUserName());*/

    }
}
