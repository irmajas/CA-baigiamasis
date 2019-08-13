package lt.codeacademy.cauzduotis.controller;

import lt.codeacademy.cauzduotis.services.IrasasRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class IrasasControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private IrasasRep irasasRep;

    @Test
    public void addIrasasTest() throws Exception {

        mockMvc.perform(post("/irasas/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content("{\"pavadinimas\": \"MOIAU?\",\"tekstas\": \"Jau kuris laikas netyla kalbos apie Facebook naujienų srauto algoritmo atnaujinimus\"}"))
                .andExpect(status().is2xxSuccessful());
        assertEquals(4, irasasRep.findAll().size());

    }

    @Test
    public void getallTest()throws Exception{
        mockMvc.perform(get("/irasas")).andExpect(status().is2xxSuccessful());

    }
}