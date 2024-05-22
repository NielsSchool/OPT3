import java.util.List;

public class Wasprogramma {
    private int aantalMinuten;
    private List<Boolean> opties;
    private String naam;
    private String omschrijving;

    public Wasprogramma(int aantalMinuten, List<Boolean> opties, String naam, String omschrijving) {
        this.aantalMinuten = aantalMinuten;
        this.naam = naam;
        this.omschrijving = omschrijving;
        this.opties = opties;
    }

    public boolean isHeeftDrogerNodig() {
        return opties.get(0);
    }

    public boolean isMoetInIndustrieleMachine() {
        return opties.get(1);
    }

    public boolean isMogelijkheidEigenWasmiddel() {
        return opties.get(2);
    }

    public int getAantalMinuten() {
        return aantalMinuten;
    }

    public String getNaam() {
        return naam;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
}
