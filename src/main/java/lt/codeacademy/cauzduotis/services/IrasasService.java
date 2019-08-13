package lt.codeacademy.cauzduotis.services;

import lt.codeacademy.cauzduotis.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IrasasService {
    private static final org.apache.logging.log4j.Logger LOG = (Logger) LogManager.getLogger( KomentarasService.class );
    private final IrasasRep irasasRep;
    @Autowired
    KomentarasService komentarasService;

    @Autowired
    public IrasasService(IrasasRep irasasRep) {
        this.irasasRep = irasasRep;
    }


    //******************Get all Blogs's list
    public List<IrasasSimple> getAllIrasas() {
        List<IrasasSimple> irasai = new ArrayList<>();
        irasai = irasasRep.findAll()
                .stream().sorted( Comparator.comparing( Irasas::getIrasodata ).reversed() )
                .map( this::mapToSimple )
                .collect( Collectors.toList() );

        return irasai;
    }
    //***********************Get latest Blog ID
    public Latest getLatestId() {
        IrasasSimple latest = getAllIrasas().stream().findFirst().get();

        return new Latest( latest.getId() );
    }

    //*******************Gauti viena
    public IrasasView getOne(int id) {
        IrasasView iras = mapToView( irasasRep.getOne( id ) );
        return iras;
    }

    //******************Prideti irasa
    public IrasasView addIrasas(IrasasView irasasView) {
        Irasas irasasSaugoti = mapFromDB( irasasView );
        irasasSaugoti.setIrasodata( LocalDateTime.now() );
        LOG.info( "Post id: " + irasasView.getId() + " with title:" + irasasSaugoti.getPavadinimas() + "  is added" );
        return mapToView( irasasRep.save( irasasSaugoti ) );
    }

//******************Edit Blog

    public IrasasView updateIrass(int id, IrasasView updatedIrasas) {
        Irasas irasas = find( id );
        if (updatedIrasas.getTekstas() != null) {
            irasas.setTekstas( updatedIrasas.getTekstas() );
        }
        if (updatedIrasas.getPavadinimas() != null) {
            irasas.setPavadinimas( updatedIrasas.getPavadinimas() );
        }
        irasas.setIrasodata( LocalDateTime.now().now() );
        irasasRep.save( irasas );
        LOG.info( "Post id: " + irasas.getId() + "  with title: " + irasas.getPavadinimas() + "  is edited" );
        return mapToView( irasas );
    }

    //********************Delete Blog
    public void deleteIrasas(int id) {
        String titlas = irasasRep.getOne( id ).getPavadinimas();
        List<KomentarasView> kometarai = komentarasService.findallForIrasas( id );
        for (KomentarasView koment : kometarai) {
            komentarasService.deleteKoment( koment.getId() );
        }
        LOG.info( "Post id: " + id + "with title:" + titlas + "deleted" );
        irasasRep.deleteById( id );
    }

    public Irasas find(int id) {
        return irasasRep.findById( id )
                .orElseThrow( () -> new NotFoundException( "Irasas nerastas" ) );
    }

    private IrasasView mapToView(Irasas irasas) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );
        return new IrasasView( irasas.getId(), irasas.getPavadinimas(), irasas.getTekstas(), irasas.getIrasodata().format( format ) );
    }

    private IrasasSimple mapToSimple(Irasas irasas) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );
        String trumpas = irasas.getTekstas().length() < 150 ? irasas.getTekstas() : irasas.getTekstas().substring( 0, 150 );
        return new IrasasSimple( irasas.getId(), irasas.getPavadinimas(), trumpas, irasas.getIrasodata().format( format ) );
    }

    private Irasas mapFromDB(IrasasView irasasView) {
        return new Irasas( irasasView.getPavadinimas(), irasasView.getTekstas() );
    }
}
