import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Catalogo {
    private HashMap<String, ElementoCatalogo> catalogo;

    public HashMap<String, ElementoCatalogo> getCatalogo() {
        return catalogo;
    }


    public Catalogo() {
        this.catalogo = new HashMap<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) throws CatalogoException {
        if(catalogo.containsKey(elemento.getCodiceISBN())) {
            throw new CatalogoException("Non è stato aggiunto nessun elemento, codice ISBN già presente.");
        } else {
            catalogo.put(elemento.getCodiceISBN(), elemento);
        }

    }


    public void rimuoviElemento(String codiceISBN) throws CatalogoException {
        if( catalogo.remove(codiceISBN) == null ) {
            throw new CatalogoException("Non è stato eliminato nessun elemento, codice ISBN non presente.");
        } else {
            catalogo.remove(codiceISBN);
        }

    }

    public ElementoCatalogo ricercaPerISBN(String codiceISBN) throws CatalogoException {
        if (catalogo.get(codiceISBN) == null){
            throw new CatalogoException("Non è stato trovato nessun elemento con il codice ISBN fornito.");
        } else {
            return catalogo.get(codiceISBN);
        }
    }

    public List<ElementoCatalogo> cercaPerAnnoPubblicazione(String anno) throws CatalogoException {

        List<ElementoCatalogo> elementi = catalogo.values().stream().filter(elemento->elemento.getAnnoPubblicazione().equals(anno)).collect(Collectors.toList());
        if(elementi.isEmpty()){
            throw new CatalogoException("Non è stato trovato nessun elemento pubblicato nell'anno fornito.");
        } else {
            return elementi;
        }
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) throws CatalogoException{
        List<ElementoCatalogo> elementi = catalogo.values().stream().filter(elemento->elemento instanceof Libro && ((Libro) elemento).getAutore().equals(autore)).collect(Collectors.toList());
        if(elementi.isEmpty()){
            throw new CatalogoException("Non è stato trovato nessun libro relativo all'autore fornito.");
        } else {
            return elementi;
        }
    }

    @Override
    public String toString() {
        return "Catalogo{" +
                "catalogo=" + catalogo +
                '}';
    }
}
