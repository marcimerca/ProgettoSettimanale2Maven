public class Rivista extends ElementoCatalogo {
    Periodicita periodicita;

    public Rivista(String codiceISBN, String titolo, String annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + "Rivista{" +
                "periodicita=" + periodicita +
                '}'+ ' ' + '}';
    }
}
