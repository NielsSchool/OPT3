import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Initializer.InitialiseerContent(); // Roep de initializeContent-methode aan
        Gebruiker gebruiker = new Gebruiker();

        while (true) {
            Boolean keuze = gebruiker.stelJaNeeVraag("Wil je een nieuwe was starten? (J/N)");
            if (keuze) {
                startNieuweWas(gebruiker);
            } else {
                print("Huidige wasbeurten:");
                Wasbeurt.printHuidigeBonnen();
            }
        }
    }

    private static void startNieuweWas(Gebruiker gebruiker) {
        Wasbeurt wasbeurt = verwerkNieuweWas(gebruiker);
        if (wasbeurt != null) {
            print("Uw was is gestart. Boncode: " + wasbeurt.getBonCode());
        }
    }

    private static Wasbeurt verwerkNieuweWas(Gebruiker gebruiker) {
        Boolean jaNee = gebruiker.stelJaNeeVraag("Welkom bij het wasprogramma. Wil je je eigen wasmiddel gebruiken? (J/N)");
    
        if (jaNee) {
            return verwerkWasmachine(gebruiker, "Bedankt voor het gebruiken van je eigen wasmiddel.", Arrays.asList(false, false, true));
        } else {
            jaNee = gebruiker.stelJaNeeVraag("Wil je de droger gebruiken? (J/N)");
            if (jaNee) {
                return verwerkWasmachine(gebruiker, "Bedankt voor het gebruik van de wasmachine met droger.", Arrays.asList(true, false, false));
            } else {
                String invoer = gebruiker.vraagKiloWas();
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
        
    

    
    private static Wasbeurt verwerkWasmachine(Gebruiker gebruiker, String keuze, List<Boolean> opties) {
        print(keuze);
        Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(opties);
        if (beschikbareWasmachine != null) {
            print("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
            Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
            return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
        }
        return null;
    }

    private static void print(String melding) {
        System.out.println(melding);
    }
}