import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WasmachineTest {
    //arrange
    private Wasmachine wasm1, wasm2;
    private WasMachineIndustrieel wasm3;
    private Wasprogramma wp1, wp2, wp3;
    private Gebruiker gebruiker;
    @BeforeEach
    void setUp() {
        Gebruiker gebruiker = new Gebruiker();
        wasm1 = new WasmachineMetDroger(1, "Keuken");
        wasm2 = new WasMachineCompact(2, "Badkamer", true);
        wasm3 = new WasMachineIndustrieel(2, "industrieel");
        wp1 = new Wasprogramma(120, Arrays.asList(true, false, false), "wasprogramma", "wasprogramma1");
        wp2 = new Wasprogramma(90, Arrays.asList(false, true, false), "wasprogramma", "wasprogramma2");
        wp3 = new Wasprogramma(60, Arrays.asList(false, false, true), "wasprogramma", "wasprogramma3");
        wasm1.addWasprogramma(wp1);
        wasm1.addWasprogramma(wp2);
        wasm2.addWasprogramma(wp3);
        wasm3.addWasprogramma(wp2);
        wasm3.setGewichtRange(5,20);
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
        // Testen of de beschikbaarheid van de wasmachine correct wordt geüpdate
        wasm1.updateWasmachineStatus(false);
        assertFalse(wasm1.getBeschikbaar());
        wasm1.updateWasmachineStatus(true);
        assertTrue(wasm1.getBeschikbaar());
    }

    @Test
    void checkbeschikbaarheidMCDC() {
        List<List<Boolean>> opties = Arrays.asList(
                // optie 1, optie 2, optie 3, decision/ verwachting
                Arrays.asList(true, false, false, true),  //true
                Arrays.asList(false, true, false, true),  //true
                Arrays.asList(false, false, true, true),   //true
                Arrays.asList(false, false, false, false) //wasmachine is null dus niet mogelijk
                //Arrays.asList(true, true, false, false), //wasmachine is null dus niet mogelijk
                //Arrays.asList(false, true, true, false), //wasmachine is null dus niet mogelijk
                //Arrays.asList(true, false, true, false), //wasmachine is null dus niet mogelijk
        );

        for (List<Boolean> optie : opties) {
            Boolean expected = optie.get(3);
            Wasmachine result = Wasmachine.CheckBeschikbaarheid(Arrays.asList(optie.get(0), optie.get(1), optie.get(2)));
            int aantalTrue = 0;
            for (int i=0; i<3; i++) {
                if (optie.get(i)) {
                    aantalTrue += 1;
                }
            }
            if (aantalTrue != 1) {
                assertNull(result);
            } else {
            assertEquals(expected, result.getBeschikbaar());
            }
        }
    }
//equivalentieklasse
    @Test
    public void testGewichtGeschikt() {
        wasm3.setGewichtRange(10, 20);
        assertTrue(wasm3.checkGewicht(15));
    }

    @Test
    public void testGewichtTelaag() {
        wasm3.setGewichtRange(10, 20);
        assertFalse(wasm3.checkGewicht(5));
    }

    @Test
    public void testGewichtTehoog() {
        wasm3.setGewichtRange(10, 20);
        assertFalse(wasm3.checkGewicht(25));
    }

    @Test
    void testCheckWasbeurtKlaarDC() {
        Wasprogramma wpKlaar = new Wasprogramma(0, Arrays.asList(true, false, false), "Normaal", "Standaard wasprogramma");
        Wasbeurt wasbeurt = new Wasbeurt(wasm1, wp1);
        assertFalse(wasbeurt.checkWachttijdOver());
        wasbeurt = new Wasbeurt(wasm1, wpKlaar);
        assertTrue(wasbeurt.checkWachttijdOver());
    }
    @Test
    void CheckBeschikbaarheid_Pairwise() {
        int[] gewichten = {1, 1, 10, 10, 25, 25};
        boolean[][] combinaties = {
                {true, true, true},    // < 5 kg, combinatie 1
                {false, false, false}, // < 5 kg, combinatie 2
                {true, false, true},   // tussen 5-20 kg, combinatie 1
                {false, true, false},  // tussen 5-20 kg, combinatie 2
                {true, true, false},   // > 20 kg, combinatie 1
                {false, false, true}   // > 20 kg, combinatie 2
        };
        boolean[] verwachtingen = {false, false, false, true, false, false};

        for (int i = 0; i < combinaties.length; i++) {
            boolean[] combinatie = combinaties[i];
            boolean verwacht = verwachtingen[i];
            int gewicht = gewichten[i];
            Wasmachine wasmBesch = Wasmachine.CheckBeschikbaarheid(Arrays.asList(combinatie[0], combinatie[1], combinatie[2]));
            if (wasmBesch instanceof WasMachineIndustrieel) {
                if (verwacht) {
                    assertNotNull(wasmBesch);
                    assertTrue(((WasMachineIndustrieel) wasmBesch).checkGewicht(gewicht));
                } else {
                    assertNull(wasmBesch);
                }
            }
        }
    }

}