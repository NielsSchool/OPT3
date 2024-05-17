public class Initializer {
    public static void InitialiseerContent() {
        Wasprogramma wasprogramma1 = new Wasprogramma(120, false, false, true, "Klein | 80 graden C | zonder droger", "Deze was is 80 graden en wast minder dan 5 kilo kleding waarbij kleding zelf gedroogt moet worden.");
        Wasprogramma wasprogramma2 = new Wasprogramma(100, false, false, true, "Klein | 60 graden C | zonder droger", "Deze was is 60 graden en wast minder dan 5 kilo kleding waarbij kleding zelf gedroogt moet worden.");
        Wasprogramma wasprogramma3 = new Wasprogramma(80, false, true, false, "Groot | 80 graden C | zonder droger", "Deze was is 80 graden en wast minder dan 20 kilo kleding waarbij kleding zelf gedroogt moet worden.");
        Wasprogramma wasprogramma4 = new Wasprogramma(60, false, true, false, "Groot | 60 graden C | zonder droger", "Deze was is 60 graden en wast minder dan 20 kilo kleding waarbij kleding zelf gedroogt moet worden.");
        Wasprogramma wasprogramma5 = new Wasprogramma(150, true, false, false, "| 80 graden C | met droger", "Deze was is 80 graden en wast minder dan 8 kilo kleding waarbij kleding gedroogt wordt.");
        Wasprogramma wasprogramma6 = new Wasprogramma(100, true, false, false, "| 60 graden C | met droger", "Deze was is 60 graden en wast minder dan 8 kilo kleding waarbij kleding gedroogt wordt.");
        //initialiseer wasmachines
        Wasmachine wasmachineCompact1 = new WasMachineCompact(1, " eerste wasmachine links.", true);
        Wasmachine wasmachineCompact2 = new WasMachineCompact(2, " tweede wasmachine links.", true);
        Wasmachine wasmachineCompact3 = new WasMachineCompact(3, " derde wasmachine links.", true);
        Wasmachine WasMachineIndustrieel1 = new WasMachineIndustrieel(4, " vierde wasmachine links");
        Wasmachine WasMachineIndustrieel2 = new WasMachineIndustrieel(5, " vijfde wasmachine links");
        Wasmachine WasMachineIndustrieel3 = new WasMachineIndustrieel(6, " zesde wasmachine links");
        Wasmachine WasMachineMetDroger1 = new WasmachineMetDroger(7, " eerste wasmachine rechts");
        Wasmachine WasMachineMetDroger2 = new WasmachineMetDroger(8, " tweede wasmachine rechts");
        Wasmachine WasMachineMetDroger3 = new WasmachineMetDroger(9, " derde wasmachine rechts");
        for (int i = 0; i < Wasmachine.Wasmachines.size(); i++) {
            if (Wasmachine.Wasmachines.get(i) instanceof WasmachineMetDroger) {
                Wasmachine.Wasmachines.get(i).addWasprogramma(wasprogramma5);
                Wasmachine.Wasmachines.get(i).addWasprogramma(wasprogramma6);
            } else if(Wasmachine.Wasmachines.get(i) instanceof WasMachineCompact) {
                Wasmachine.Wasmachines.get(i).addWasprogramma(wasprogramma1);
                Wasmachine.Wasmachines.get(i).addWasprogramma(wasprogramma2);
            } else if(Wasmachine.Wasmachines.get(i) instanceof WasMachineIndustrieel) {
                Wasmachine.Wasmachines.get(i).addWasprogramma(wasprogramma3);
                Wasmachine.Wasmachines.get(i).addWasprogramma(wasprogramma4);
            }
        }
    }
    
}
