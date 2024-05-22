public class WasmachineMetDroger extends Wasmachine {
    public WasmachineMetDroger(int nr, String locatie) {
        super(nr, locatie);
    }

    @Override
    public void voorWas() {
    }

    @Override
    public void hoofdWas() {
    }

    @Override
    public void naWas() {
        startDrogen();
    }

    public void startDrogen() {
        this.updateWasmachineStatus(true);
    }
}
