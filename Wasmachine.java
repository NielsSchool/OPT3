import java.util.ArrayList;
import java.util.List;

public abstract class Wasmachine {
    private int nr;
    private String locatie;
    private boolean beschikbaar;
    private int cooldown;
    public static ArrayList<Wasmachine> Wasmachines = new ArrayList<Wasmachine>();
    private ArrayList<Wasprogramma> wasprogrammas = new ArrayList<Wasprogramma>();
    public Wasmachine(int nr, String locatie) {
        this.nr = nr;
        this.locatie = locatie;
        this.beschikbaar = true;
        Wasmachines.add(this);
    }
    public boolean getBeschikbaar() {
        return beschikbaar;
    }
    public void addWasprogramma(Wasprogramma wasprogramma) {
        this.wasprogrammas.add(wasprogramma);
    }
    public ArrayList<Wasprogramma> getWasprogrammas() {
        return wasprogrammas;
    }
    public String getLocatie() {
        return locatie;
    }
    public static Wasmachine CheckBeschikbaarheid(List<Boolean> opties) {
        Wasmachine wasmachineBeschikbaar = null;
        for (Wasmachine wasmachine:Wasmachine.Wasmachines) {
            if(wasmachine.getBeschikbaar()) {
                for (Wasprogramma wasprogramma:wasmachine.getWasprogrammas()) {
                    if(wasprogramma.isHeeftDrogerNodig() == opties.get(0) && wasprogramma.isMoetInIndustrieleMachine() == opties.get(1) && wasprogramma.isMogelijkheidEigenWasmiddel() == opties.get(2)) {
                        wasmachineBeschikbaar = wasmachine;
                    }
                }
            }
        }
        return wasmachineBeschikbaar;
    }

    public Wasmachine startWasmachine(Wasprogramma wasprogramma) {
        if (this.beschikbaar) {
            voorWas();
            this.cooldown = wasprogramma.getAantalMinuten();
            this.updateWasmachineStatus(false);
            hoofdWas();
            //als nawas nodig is deze wel of niet implementeren? <-- mogelijke uitbreiding
            naWas();
            return this;
        } else {
            return null;
        }
    }
    public void updateWasmachineStatus(boolean beschikbaar) {
        this.beschikbaar=beschikbaar;
    }

    public abstract void voorWas();
    public abstract void hoofdWas();
    public abstract void naWas();
}