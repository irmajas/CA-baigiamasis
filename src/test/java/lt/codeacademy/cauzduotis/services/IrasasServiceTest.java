package lt.codeacademy.cauzduotis.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
//@DataJpaTest
@ContextConfiguration(classes = {IrasasService.class})
//@EnableAutoConfiguration
public class IrasasServiceTest {

    @Autowired
    private IrasasService irasasService;
    @MockBean
     private IrasasRep irasasRep;
    @Test
    public void updateIrass() {

    }
}