import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WasmachineTest {
    //arrange
    private Wasmachine wasm1, wasm2;
    private Wasprogramma wp1, wp2, wp3;
    @BeforeEach
    void setUp() {
        wasm1 = new Wasmachine(1, "Keuken");
        wasm2 = new Wasmachine(2, "Badkamer");
        wp1 = new Wasprogramma(120, Arrays.asList(true, false, false), "wasprogramma", "wasprogramma1");
        wp2 = new Wasprogramma(90, Arrays.asList(false, true, false), "wasprogramma", "wasprogramma2");
        wp3 = new Wasprogramma(60, Arrays.asList(false, false, true), "wasprogramma", "wasprogramma3");
        wasm1.addWasprogramma(wp1);
        wasm1.addWasprogramma(wp2);
        wasm2.addWasprogramma(wp3);
    }

    @Test
    void checkBeschikbaarheid() {
        // Testen of een beschikbare wasmachine wordt geretourneerd
        Wasmachine wasmachine = Wasmachine.CheckBeschikbaarheid(Arrays.asList(false, false, false));
        assertNull(wasmachine);
        wasmachine = Wasmachine.CheckBeschikbaarheid(Arrays.asList(true, false, false));
        assertNotNull(wasmachine);
        // Testen of er null wordt geretourneerd als er geen beschikbare wasmachines zijn
        wasm1.updateWasmachineStatus(false);
        wasm2.updateWasmachineStatus(false);
        wasmachine = Wasmachine.CheckBeschikbaarheid(Arrays.asList(false, false, false));
        assertNull(wasmachine);
        // Testen of een wasmachine wordt geretourneerd die het juiste wasprogramma kan uitvoeren
        wasm1.updateWasmachineStatus(true);
        wasmachine = Wasmachine.CheckBeschikbaarheid(Arrays.asList(true, false, false));
        assertEquals(wasmachine, wasm1);
    }

    @Test
    void startWasmachine() {
        Wasbeurt wasbeurt = new Wasbeurt(wasm1, wp1);
        assertNotNull(wasbeurt);
        assertEquals(wp1, wasbeurt.getWasprogramma());
        assertEquals(wasm1, wasbeurt.getWasmachine());
    }

    @Test
    void updateWasmachineStatus() {
        // Testen of de beschikbaarheid van de wasmachine correct wordt geüpdatet
        wasm1.updateWasmachineStatus(false);
        assertFalse(wasm1.getBeschikbaar());
        wasm1.updateWasmachineStatus(true);
        assertTrue(wasm1.getBeschikbaar());
    }

    @Test
    void startWasmachineMCDC() {
        Wasprogramma wasprogramma = new Wasprogramma(120, Arrays.asList(true, false, false), "wasprogramma", "wasprogramma1");

        wasm1.updateWasmachineStatus(true);
        assertTrue(wasm1.getBeschikbaar());

        Wasbeurt wasbeurt = new wasbeurt(wasm1, wasprogramma)
        assertFalse(wasm1.getBeschikbaar());
        assertNotNull(wasbeurt);

        assertEquals(wasprogramma, wasbeurt.getWasprogramma());
        assertEquals(wasm1, wasbeurt.getWasmachine());

        wasm1.updateWasmachineStatus(false);
        assertFalse(wasm1.getBeschikbaar());

        wasbeurt = new Wasbeurt(wasm1, wasprogramma);
        assertFalse(wasm1.getBeschikbaar());
        assertNull(wasbeurt);
    }

    @Test
    void startWasmachine_Pairwise() {
        boolean[] heeftDrogerNodigValues = { true, false };
        boolean[] moetIndustrieleMachineValues = { true, false };
        boolean[] mogelijkheidEigenWasmiddelValues = { true, false };

        for (boolean heeftDrogerNodig : heeftDrogerNodigValues) {
            for (boolean moetIndustrieleMachine : moetIndustrieleMachineValues) {
                for (boolean mogelijkheidEigenWasmiddel : mogelijkheidEigenWasmiddelValues) {
                    Wasprogramma wasprogramma = new Wasprogramma(120, Arrays.asList(heeftDrogerNodig, moetIndustrieleMachine, mogelijkheidEigenWasmiddel), "wasprogramma", "wasprogramma1");
                    wasm1 = new Wasmachine(1, "Keuken");
                    Wasbeurt wasbeurt = new Wasbeurt(wasm1, wasprogramma);
                    assertNotNull(wasbeurt);
                }
            }
        }
    }
}