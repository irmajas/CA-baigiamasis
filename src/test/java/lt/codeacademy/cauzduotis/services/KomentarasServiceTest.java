package lt.codeacademy.cauzduotis.services;

import lt.codeacademy.cauzduotis.models.Irasas;
import lt.codeacademy.cauzduotis.models.Komentaras;
import lt.codeacademy.cauzduotis.models.KomentarasView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = {KomentarasService.class})
//@EnableAutoConfiguration
public class KomentarasServiceTest {
    //    @Autowired
//    private KomentarasRep komentarasRep;
    @Autowired
    private KomentarasService komentarasService;

    @MockBean
    private KomentarasRep komentarasRep;
    @MockBean
    private IrasasService irasasService;
    @Test
    public void updateAtsakymas() {
        Irasas irass=new Irasas("ajsdhk","sjhdgjf");
        irass.setId(1);
        Komentaras komentaras = new Komentaras("aaa","bbbb","");
        komentaras.setId(1);
        komentaras.setKomentdata(LocalDateTime.now());
        komentaras.setIrasas(irass);
        KomentarasView komentrasAts = new KomentarasView(1,"","","ggggg","2019-02-01 14:55");
        komentaras.setId(1);
        komentaras.setKomentdata(LocalDateTime.now());
        komentaras.setIrasas(irass);
        when(komentarasRep.findById( 1 )).thenReturn(java.util.Optional.of(komentaras));
      //  when(irasasService.find(1)).thenReturn(irass);
     KomentarasView komentarasView =komentarasService.updateAtsakymas(1,komentrasAts);


      assertEquals("ggggg",komentarasView.getAtsakymas());

    }
    @Test
    public void findallForIrasasTest() {
        Irasas irass=new Irasas("ajsdhk","sjhdgjf");
        irass.setId(1);
        Komentaras komentaras = new Komentaras("aaa","bbbb","swhdw");
        komentaras.setId(1);
        komentaras.setKomentdata(LocalDateTime.now());
        komentaras.setIrasas(irass);
        when(komentarasRep.findByIrasasId(1)).thenReturn(Collections.singletonList(komentaras));
        when(irasasService.find(1)).thenReturn(irass);
        List<KomentarasView> komentarasViewList =komentarasService.findallForIrasas(1);
        assertEquals(1,komentarasViewList.size());
        assertNotNull(komentarasViewList);
        assertEquals(komentaras.getAtsakymas(),komentarasViewList.get(0).getAtsakymas());

    }
}