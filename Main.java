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
                Wasbeurt.printHuidigeWasbeurten();
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
                int kilos = 0;
                if (invoer.equalsIgnoreCase("A")) {
                    kilos = 5;
                } else if (invoer.equalsIgnoreCase("B")) {
                    kilos = 10;
                } else if (invoer.equalsIgnoreCase("C")) {
                    kilos = 20;
                }
                return verwerkWasmachine(gebruiker, "Bedankt voor het wassen van "+ kilos +" kilo was.", Arrays.asList(false, true, false));
            }
        }
    }
        
    

    
    private static Wasbeurt verwerkWasmachine(Gebruiker gebruiker, String keuze, List<Boolean> opties) {
        print(keuze);
        Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(opties);
        if (beschikbareWasmachine != null) {
            print("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
            Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
            Wasmachine gekozenWasmachine = beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
            Wasbeurt wasbeurt = new Wasbeurt(gekozenWasmachine, gekozenWasprogramma);
            gebruiker.startWasbeurt(wasbeurt);
            return wasbeurt;
        }
        return null;
    }

    private static void print(String melding) {
        System.out.println(melding);
    }
}