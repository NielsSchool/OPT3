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
    void ConditionCoverage() {
        List<List<Boolean>> opties = Arrays.asList(
                // optie 1, optie 2, optie 3, decision/ verwachting
                Arrays.asList(false, false, false, false),
                Arrays.asList(true, true, true, false)
        );

        for (List<Boolean> optie : opties) {
            Boolean expected = optie.get(3);
            Wasmachine result = Wasmachine.CheckBeschikbaarheid(Arrays.asList(optie.get(0), optie.get(1), optie.get(2)));
            int aantalTrue = 0;
            for (int i = 0; i < 3; i++) {
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
    @Test
    void ConditionDecision() {
        List<List<Boolean>> opties = Arrays.asList(
                // optie 1, optie 2, optie 3, decision/ verwachting
                Arrays.asList(true, false, false, true),
                Arrays.asList(false, true, true, false)
        );

        for (List<Boolean> optie : opties) {
            Boolean expected = optie.get(3);
            Wasmachine result = Wasmachine.CheckBeschikbaarheid(Arrays.asList(optie.get(0), optie.get(1), optie.get(2)));
            int aantalTrue = 0;
            for (int i = 0; i < 3; i++) {
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
    @Test
    void checkbeschikbaarheidMCDC() {
        List<List<Boolean>> opties = Arrays.asList(
                // optie 1, optie 2, optie 3, decision/ verwachting
                Arrays.asList(true, false, false, true),
                Arrays.asList(false, true, false, true),
                Arrays.asList(false, false, true, true),
                Arrays.asList(false, false, false, false)
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
    public void checkGewichtRandwaarden() {
        int[] testgevallen = {4,5,6,19,20,21};
        boolean[] verwachtingen = {false, true, true, true, true, false};
        for (int i = 0; i < 6; i++) {
            assertEquals(wasm3.checkGewicht(testgevallen[i]), verwachtingen[i]);
        }
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