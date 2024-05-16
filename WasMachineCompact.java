public class WasMachineCompact extends Wasmachine {
    private boolean eigenWasmiddel;

    public WasMachineCompact(int nr, String locatie, boolean eigenWasmiddel) {
        super(nr, locatie);
        this.eigenWasmiddel = eigenWasmiddel;
    }

    public void startWasMetEigenWasmiddel() {
        //mogelijke implementatie voor uitbreidingen met bijvoorbeeld behulp van super.startwasmachine bijvoorbeeld voor eigen wasmiddel gebruiken
    }
}
