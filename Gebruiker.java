import java.util.ArrayList;
import java.util.Scanner;

public class Gebruiker implements Observer {
    private ArrayList<Wasbeurt> wasbeurten = new ArrayList<>(); 
    // private Gebruiker huidigeGebruiker;
    public Boolean stelJaNeeVraag(String vraag) {
        Scanner sc = new Scanner(System.in);
        String invoer = "";
        while (!invoer.equalsIgnoreCase("J") && !invoer.equalsIgnoreCase("N")) {
            System.out.println(vraag);
            invoer = sc.nextLine();
        }
        return invoer.equalsIgnoreCase("J");
    }

    public String vraagKiloWas() {
        Scanner sc = new Scanner(System.in);
        String invoer = "";
        while (!invoer.equalsIgnoreCase("A") && !invoer.equalsIgnoreCase("B") && !invoer.equalsIgnoreCase("C")) {
            System.out.println("Hoeveel kilo was wil je wassen? (A/B/C)");
            invoer = sc.nextLine();
        }
        return invoer;
    }

    public Wasprogramma invoerWasprogramma(Wasmachine beschikbareWasmachine) {
        Scanner sc = new Scanner(System.in);
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

    @Override
    public void update(Wasbeurt wasbeurt) {
        System.out.println("Wasbeurt nr. " + wasbeurt.getBonCode() + " is klaar om opgehaald te worden.");
        wasbeurten.remove(wasbeurt);
    }

    public void startWasbeurt(Wasbeurt wasbeurt) {
        wasbeurt.addObserver(this);
        wasbeurten.add(wasbeurt);
    }
}
