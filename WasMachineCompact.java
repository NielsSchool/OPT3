public class WasMachineCompact extends Wasmachine {
    private boolean eigenWasmiddel;

    public WasMachineCompact(int nr, String locatie, boolean eigenWasmiddel) {
        super(nr, locatie);
        this.eigenWasmiddel = eigenWasmiddel;
    }
    @Override
    public void voorWas() {
        if (eigenWasmiddel) {
            eigenWasmiddelToevoegen();
        }
    }

    @Override
    public void hoofdWas() {
    }

    @Override
    public void naWas() {
    }
    public void eigenWasmiddelToevoegen() {
        //mogelijke implementatie voor uitbreidingen met bijvoorbeeld behulp van super.startwasmachine bijvoorbeeld voor eigen wasmiddel gebruiken
    }
}
