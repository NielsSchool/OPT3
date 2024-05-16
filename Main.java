import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InitialiseerContent();
        while (true) {
            String keuze = vraagNieuweWas(sc);
            if (keuze.equalsIgnoreCase("J")) {
                startNieuweWas(sc);
            } else {
                System.out.println("Huidige bonnen:");
                Bon.printHuidigeBonnen();
            }
        }
    }

    private static String vraagNieuweWas(Scanner sc) {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println("Wil je een nieuwe was starten? (J/N)");
            invoer = sc.nextLine();
        }
        return invoer;
    }

    private static void startNieuweWas(Scanner sc) {
        Bon bon = verwerkNieuweWas(sc);
        if (bon != null) {
            System.out.println("Uw was is gestart. Boncode: " + bon.getBonCode());
        }
    }

    private static Bon verwerkNieuweWas(Scanner sc) {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println("Welkom bij het wasprogramma. Wil je je eigen wasmiddel gebruiken? (J/N)");
            invoer = sc.nextLine();
        }

        if (invoer.equalsIgnoreCase("J")) {
            System.out.println("Bedankt voor het gebruiken van je eigen wasmiddel.");
            Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(false, false, true);
            if (beschikbareWasmachine != null) {
                System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                Wasprogramma gekozenWasprogramma = invoerWasprogramma(sc, beschikbareWasmachine);
                return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
            }
        } else {
            invoer = "";
            while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
                System.out.println("Wil je de droger gebruiken? (J/N)");
                invoer = sc.nextLine();
            }

            if (invoer.equalsIgnoreCase("J")) {
                System.out.println("Bedankt voor het gebruik van de droger.");
                Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(true, false, false);
                if (beschikbareWasmachine != null) {
                    System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                    Wasprogramma gekozenWasprogramma = invoerWasprogramma(sc, beschikbareWasmachine);
                    return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                }
            } else {
                invoer = "";
                while (!invoer.equalsIgnoreCase("A") && !invoer.equalsIgnoreCase("B") && !invoer.equalsIgnoreCase("C")) {
                    System.out.println("Hoeveel kilo was wil je wassen? (A/B/C)");
                    invoer = sc.nextLine();
                }

                if (invoer.equalsIgnoreCase("A")) {
                    System.out.println("Bedankt voor het wassen van 5 kilo was.");
                    Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(true, true, true);
                    if (beschikbareWasmachine != null) {
                        return beschikbareWasmachine.startWasmachine(invoerWasprogramma(sc, beschikbareWasmachine));
                    }
                } else if (invoer.equalsIgnoreCase("B")) {
                    System.out.println("Bedankt voor het wassen van 8 kilo was.");
                    Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(true, true, false);
                    if (beschikbareWasmachine != null) {
                        Wasprogramma gekozenWasprogramma = invoerWasprogramma(sc, beschikbareWasmachine);
                        return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                    }
                } else {
                    System.out.println("Bedankt voor het wassen van 20 kilo was.");
                    Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(false, true, false);
                    if (beschikbareWasmachine != null) {
                        Wasprogramma gekozenWasprogramma = invoerWasprogramma(sc, beschikbareWasmachine);
                        return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                    }
                }
            }
        }

        System.out.println("Geen wasmachine beschikbaar op dit moment.");
        return null;
    }
    private static Wasprogramma invoerWasprogramma(Scanner sc, Wasmachine wasmachine) {
        int index = 0;
        int input = -1;
        System.out.println(tekst("KiesWasprogramma"));
        for (Wasprogramma wasprogramma: wasmachine.getWasprogrammas()) {
            System.out.println("[" + index + "] " + wasprogramma.getNaam());
            index +=1;
        }
        while(input < 0 | input > wasmachine.getWasprogrammas().size()) {
            input = sc.nextInt();
        }
        Wasprogramma gekozenWasprogramma = wasmachine.getWasprogrammas().get(input);
        return gekozenWasprogramma;
    }
    private static String tekst(String key) {
        return Spreektaal.getTekst(key);
    }
    private static void InitialiseerContent() {
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