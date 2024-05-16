public class Wasprogramma {
    private int aantalMinuten;
    private boolean heeftDrogerNodig;
    private boolean moetInIndustrieleMachine;
    private boolean mogelijkheidEigenWasmiddel;
    private String naam;
    private String omschrijving;

    public Wasprogramma(int aantalMinuten, boolean heeftDrogerNodig, boolean moetInIndustrieleMachine, boolean mogelijkheidEigenWasmiddel, String naam, String omschrijving) {
        this.aantalMinuten = aantalMinuten;
        this.heeftDrogerNodig = heeftDrogerNodig;
        this.moetInIndustrieleMachine = moetInIndustrieleMachine;
        this.mogelijkheidEigenWasmiddel = mogelijkheidEigenWasmiddel;
        this.naam = naam;
        this.omschrijving = omschrijving;
    }

    public boolean isHeeftDrogerNodig() {
        return heeftDrogerNodig;
    }

    public boolean isMoetInIndustrieleMachine() {
        return moetInIndustrieleMachine;
    }

    public boolean isMogelijkheidEigenWasmiddel() {
        return mogelijkheidEigenWasmiddel;
    }

    public int getAantalMinuten() {
        return aantalMinuten;
    }

    public String getNaam() {
        return naam;
    }

    public static Bon startWasprogramma(Wasprogramma wasprogramma, Wasmachine wasmachine) {
        Bon bon = new Bon(wasmachine, wasprogramma);
        return bon;
    }

    public String getOmschrijving() {
        return omschrijving;
    }
}
