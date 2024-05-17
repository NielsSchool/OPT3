import java.util.ArrayList;
import java.util.Scanner;

public class Gebruiker {
    private Scanner sc;
    // private Gebruiker huidigeGebruiker;

    public Gebruiker() {
        this.sc = new Scanner(System.in);
    }

    public String vraagNieuweWas() {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println("Wil je een nieuwe was starten? (J/N)");
            invoer = sc.nextLine();
        }
        return invoer;
    }

    public String vraagEigenWasmiddel() {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println("Welkom bij het wasprogramma. Wil je je eigen wasmiddel gebruiken? (J/N)");
            invoer = sc.nextLine();
        }
        return invoer;
    }

    public String vraagDrogerGebruik() {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println("Wil je de droger gebruiken? (J/N)");
            invoer = sc.nextLine();
        }
        return invoer;
    }

    public String vraagKiloWas() {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("A") && !invoer.equalsIgnoreCase("B") && !invoer.equalsIgnoreCase("C")) {
            System.out.println("Hoeveel kilo was wil je wassen? (A/B/C)");
            invoer = sc.nextLine();
        }
        return invoer;
    }

    public Wasprogramma invoerWasprogramma(Wasmachine beschikbareWasmachine) {
        System.out.println("Beschikbare wasprogramma's:");
        ArrayList<Wasprogramma> wasprogrammas = beschikbareWasmachine.getWasprogrammas();
        for (int i = 0; i < wasprogrammas.size(); i++) {
            Wasprogramma wasprogramma = wasprogrammas.get(i);
            System.out.println((i + 1) + ": " + wasprogramma.getOmschrijving());
        }
        System.out.println("Kies een wasprogramma (nummer):");
        int keuze = sc.nextInt();
        sc.nextLine(); // consume newline
        return wasprogrammas.get(keuze - 1);
    }
}
