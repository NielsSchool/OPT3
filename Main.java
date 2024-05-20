import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Initializer.InitialiseerContent(); // Roep de initializeContent-methode aan
        Gebruiker gebruiker = new Gebruiker();

        while (true) {
            String keuze = gebruiker.vraagNieuweWas();
            if (keuze.equalsIgnoreCase("J")) {
                startNieuweWas(gebruiker);
            } else {
                System.out.println("Huidige bonnen:");
                Bon.printHuidigeBonnen();
            }
        }
    }

    private static void startNieuweWas(Gebruiker gebruiker) {
        Bon bon = verwerkNieuweWas(gebruiker);
        if (bon != null) {
            System.out.println("Uw was is gestart. Boncode: " + bon.getBonCode());
        }
    }

    private static Bon verwerkNieuweWas(Gebruiker gebruiker) {
        String invoer = gebruiker.vraagEigenWasmiddel();
    
        if (invoer.equalsIgnoreCase("J")) {
            return verwerkWasmachine(gebruiker, "Bedankt voor het gebruiken van je eigen wasmiddel.", Arrays.asList(false, false, true));
        } else {
            invoer = gebruiker.vraagDrogerGebruik();
            if (invoer.equalsIgnoreCase("J")) {
                return verwerkWasmachine(gebruiker, "Bedankt voor het gebruik van de wasmachine met droger.", Arrays.asList(true, false, false));
            } else {
                invoer = gebruiker.vraagKiloWas();
                if (invoer.equalsIgnoreCase("A")) {
                    return verwerkWasmachine(gebruiker, "Bedankt voor het wassen van 5 kilo was.", Arrays.asList(false, true, false));
                } else if (invoer.equalsIgnoreCase("B")) {
                    return verwerkWasmachine(gebruiker, "Bedankt voor het wassen van 10 kilo was.", Arrays.asList(false, true, false));
                } else if (invoer.equalsIgnoreCase("C")) {
                    return verwerkWasmachine(gebruiker, "Bedankt voor het wassen van 20 kilo was.", Arrays.asList(false, true, false));
                }
            }
        }
        return null;
    }
        
    

    
    private static Bon verwerkWasmachine(Gebruiker gebruiker, String keuze, List<Boolean> opties) {
        System.out.println(keuze);
        Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(opties);
        if (beschikbareWasmachine != null) {
            System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
            Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
            return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
        }
        return null;
    }
}