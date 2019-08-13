package lt.codeacademy.cauzduotis.services;

import lt.codeacademy.cauzduotis.models.Irasas;
import lt.codeacademy.cauzduotis.models.Komentaras;
import lt.codeacademy.cauzduotis.models.KomentarasView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KomentarasService {
    private static final org.apache.logging.log4j.Logger LOG = (Logger) LogManager.getLogger( KomentarasService.class );
    private final KomentarasRep komentarasRep;


    @Autowired
    public KomentarasService(KomentarasRep komentarasRep) {
        this.komentarasRep = komentarasRep;
    }

    @Autowired
    private IrasasService irasasService;

    public List<KomentarasView> findallForIrasas(int id) {
        return komentarasRep.findByIrasasId( id )
                .stream()
                .map( this::mapToView )
                .collect( Collectors.toList() );

    }

    public KomentarasView updateAtsakymas(int id, KomentarasView updatedatsakymas) {
        Komentaras koment = find( id );
        if (updatedatsakymas.getAtsakymas() != null ) {
            koment.setAtsakymas( updatedatsakymas.getAtsakymas() );
            LOG.info( "Comment id: " + koment.getId() + " answer added " );
            komentarasRep.save( koment );
        }
        return mapToView( koment );
    }


    public KomentarasView addKomentaras(KomentarasView komentarasView, int id) {

        Komentaras tas = mapFromDB( komentarasView );
        Irasas iras = irasasService.find( id );
        tas.setIrasas( iras );

        tas.setKomentdata( LocalDateTime.now() );
        tas.setAtsakymas( "" );
        LOG.info( "Comment to Post id:" + iras.getId() + " Title: " + iras.getPavadinimas() + " added" );
        return mapToView( komentarasRep.save( tas ) );
    }

    public void deleteKoment(int id) {

        komentarasRep.deleteById( id );
    }

    private Komentaras find(int id) {
        return komentarasRep.findById( id )
                .orElseThrow( () -> new NotFoundException( "Komentras nerastas" ) );
    }

    private KomentarasView mapToView(Komentaras komentaras) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm" );

        return new KomentarasView( komentaras.getId(), komentaras.getAutorius(), komentaras.getKomentaras(), komentaras.getAtsakymas(), komentaras.getKomentdata().format( format ) );
    }

    private Komentaras mapFromDB(KomentarasView komentarasView) {

        return new Komentaras( komentarasView.getAutorius(), komentarasView.getKomentaras(), komentarasView.getAtsakymas() );
    }
}
