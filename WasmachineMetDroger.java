public class WasmachineMetDroger extends Wasmachine {
    public WasmachineMetDroger(int nr, String locatie) {
        super(nr, locatie);
    }

    public void startDrogen() {
        //cooldown/ zet status op aan het drogen
        //mogelijke implementatie voor uitbreidingen met behulp van super.startwasmachine
        this.updateWasmachineStatus(true);
    }
}
