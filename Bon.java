import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Bon {
    private int bonCode;
    private Timestamp eindTijd;
    private Wasmachine wasmachine;
    private Wasprogramma wasprogramma;
    private static ArrayList<Bon> bonnen = new ArrayList<>();

    public Bon(Wasmachine wasmachine, Wasprogramma wasprogramma) {
        this.bonCode = genereerBonCode();
        this.wasmachine = wasmachine;
        this.wasprogramma = wasprogramma;
        this.eindTijd = Timestamp.valueOf(LocalDateTime.now().plus(wasprogramma.getAantalMinuten(), ChronoUnit.MINUTES));
        bonnen.add(this);
    }

    private String checkWachttijd() {
        String wachttijd = " Minuten";
        Timestamp huidigeTijd = new Timestamp(System.currentTimeMillis());
        long differenceInMillis = this.eindTijd.getTime() - huidigeTijd.getTime();
        long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMillis);
        wachttijd = differenceInMinutes + wachttijd;
        return wachttijd;
    }

    private int genereerBonCode() {
        Random random = new Random();
        return random.nextInt(90000000) + 10000000;
    }

    public int getBonCode() {
        return bonCode;
    }

    public static void printHuidigeBonnen() {
        int index = 0;
        for (Bon bon : bonnen) {
            index += 1;
            System.out.println("[" + index + "]\nWasmachine: " + bon.wasmachine.getLocatie() +
                    "\nWasprogramma: " + bon.wasprogramma.getNaam() + "\nWachttijd: " + bon.checkWachttijd());
        }
    }

    public static int getBonnenLength() {
        return bonnen.size();
    }

    public Wasmachine getWasmachine() {
        return wasmachine;
    }

    public void setWasmachine(Wasmachine wasmachine) {
        this.wasmachine = wasmachine;
    }

    public Wasprogramma getWasprogramma() {
        return wasprogramma;
    }

    public void setWasprogramma(Wasprogramma wasprogramma) {
        this.wasprogramma = wasprogramma;
    }
}
