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
            System.out.println("Bedankt voor het gebruiken van je eigen wasmiddel.");
            Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(false, false, true);
            if (beschikbareWasmachine != null) {
                System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
                return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
            }
        } else {
            invoer = gebruiker.vraagDrogerGebruik();
            if (invoer.equalsIgnoreCase("J")) {
                System.out.println("Bedankt voor het gebruik van de droger.");
                Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(true, false, false);
                if (beschikbareWasmachine != null) {
                    System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                    Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
                    return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                }
            } else {
                invoer = gebruiker.vraagKiloWas();
                if (invoer.equalsIgnoreCase("A")) {
                    System.out.println("Bedankt voor het wassen van 5 kilo was.");
                    Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(false, true, false);
                    if (beschikbareWasmachine != null) {
                        System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                        Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
                        return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                    }
                } else if (invoer.equalsIgnoreCase("B")) {
                    System.out.println("Bedankt voor het wassen van 10 kilo was.");
                    Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(false, true, false);
                    if (beschikbareWasmachine != null) {
                        System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                        Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
                        return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                    }
                } else if (invoer.equalsIgnoreCase("C")) {
                    System.out.println("Bedankt voor het wassen van 20 kilo was.");
                    Wasmachine beschikbareWasmachine = Wasmachine.CheckBeschikbaarheid(false, true, false);
                    if (beschikbareWasmachine != null) {
                        System.out.println("Een wasmachine is beschikbaar op locatie: " + beschikbareWasmachine.getLocatie());
                        Wasprogramma gekozenWasprogramma = gebruiker.invoerWasprogramma(beschikbareWasmachine);
                        return beschikbareWasmachine.startWasmachine(gekozenWasprogramma);
                    }
                }
            }
        }
        return null;
    }
}
