package lt.codeacademy.cauzduotis.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NotFoundException extends RuntimeException {
    private static final org.apache.logging.log4j.Logger LOG = (Logger) LogManager.getLogger( NotFoundException.class );

    public NotFoundException(String message) {
        super(message);
        LOG.error( " erorr: "+message);   LOG.error( " erorr: "+message);
    }

    public NotFoundException() {
        LOG.error( " erorr: NotFoundException");
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
        LOG.error( " erorr: "+message);   LOG.error( " erorr: "+message);
    }
}
