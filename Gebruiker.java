import java.util.ArrayList;
import java.util.Scanner;

public class Gebruiker {
    private Scanner sc;
    // private Gebruiker huidigeGebruiker;

    public Gebruiker() {
        this.sc = new Scanner(System.in);
    }

    public Boolean stelJaNeeVraag(String vraag) {
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println(vraag);
            invoer = sc.nextLine();
        }
        return invoer.equalsIgnoreCase("J");
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
