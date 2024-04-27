import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.stream.Collectors;

public class UsaCatalogo {
    static Logger logger = LoggerFactory.getLogger("logger1");

    public static void main(String[] args) {

        Catalogo catalogo = new Catalogo();
        Rivista rivista1 = new Rivista("ISBN001", "Wired", "2024", 50, Periodicita.SETTIMANALE);
        Rivista rivista2 = new Rivista("ISBN002", "Focus", "2023", 60, Periodicita.MENSILE);
        Rivista rivista3 = new Rivista("ISBN003", "Espresso", "2022", 70, Periodicita.SEMESTRALE);


        Libro libro1 = new Libro("ISBN004", "L'ombra del vento", "2001", 400, "Carlos Ruiz Zafón", "Romanzo");
        Libro libro2 = new Libro("ISBN005", "Il codice da Vinci", "2003", 350, "Dan Brown", "Thriller");
        Libro libro3 = new Libro("ISBN006", "Cronache del ghiaccio e del fuoco", "2011", 600, "George R.R. Martin", "Fantasy");

        catalogo.aggiungiElemento(rivista1);
        catalogo.aggiungiElemento(rivista2);
        catalogo.aggiungiElemento(rivista3);
        catalogo.aggiungiElemento(libro1);
        catalogo.aggiungiElemento(libro2);
        catalogo.aggiungiElemento(libro3);

        System.out.println(catalogo);

        // Qui ho fatto varie prove per vedere il funzionamento delle eccezioni

        try {
            catalogo.cercaPerAnnoPubblicazione("2009");
        } catch (CatalogoException e) {
            logger.error(e.getMessage());
        }

        try {
            catalogo.cercaPerAutore("Giorgio Faletti");
        } catch (CatalogoException e) {
            logger.error(e.getMessage());
        }

        try {
            System.out.println(catalogo.ricercaPerISBN("ISBN00111"));
        } catch (CatalogoException e) {
            logger.error(e.getMessage());
        }

        try {
            catalogo.rimuoviElemento("ISnsdmnsdk");
        } catch (CatalogoException e) {
            logger.error(e.getMessage());
        }

        try {
            System.out.println(catalogo.cercaPerAutore("Dan Brown"));
        } catch (CatalogoException e) {
            logger.error(e.getMessage());
        }


        // Qui ho gestito la parte relativa alla scrittura e alla lettura del file


        String stringaCatalogo = catalogo.getCatalogo().values().stream().map(elemento -> {
            if (elemento instanceof Libro) {
                return "Libro" + "@" + elemento.getCodiceISBN() + "@" + elemento.getTitolo() + "@" + elemento.getAnnoPubblicazione() + "@" + elemento.getNumeroPagine() + "@" + ((Libro) elemento).getAutore() + "@" + ((Libro) elemento).getGenere();
            } else {
                return "Rivista" + "@" + elemento.getCodiceISBN() + "@" + elemento.getTitolo() + "@" + elemento.getAnnoPubblicazione() + "@" + elemento.getNumeroPagine() + "@" + ((Rivista) elemento).periodicita;
            }
        }).collect(Collectors.joining("#"));


        File file = new File("./salvataggio/nuovoFile.txt");

        try {
            FileUtils.writeStringToFile(file, stringaCatalogo, Charset.defaultCharset());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }





    }
}
