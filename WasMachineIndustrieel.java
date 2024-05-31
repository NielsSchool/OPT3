public class WasMachineIndustrieel extends Wasmachine {
    private int[] gewichtRange;
    public WasMachineIndustrieel(int nr, String locatie) {
        super(nr, locatie);
    }
    public void setGewichtRange(int van, int tot) {
        int[] nieuweRange = new int[2];
        nieuweRange[0]= van;
        nieuweRange[1]= tot;
        this.gewichtRange = nieuweRange;
    }

    public int[] getGewichtRange() {
        return this.gewichtRange;
    }
    @Override
    public void voorWas() {
    }

    @Override
    public void hoofdWas() {
    }

    @Override
    public void naWas() {
    }

    public boolean checkGewicht(int gewicht) {
        if (this.gewichtRange[0] <= gewicht && gewicht <= gewichtRange[1]) {
            return true;
        } else {
            return false;
        }
    }
}

