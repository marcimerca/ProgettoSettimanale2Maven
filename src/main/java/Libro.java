public class Libro extends ElementoCatalogo {
    private String autore;
    private String genere;

    public Libro(String codiceISBN, String titolo, String annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    @Override
    public String toString() {
        return super.toString() + "Libro{" +
                "autore='" + autore + '\'' +
                ", genere='" + genere + '\'' +
                '}'+ ' ' + '}';
    }
}
