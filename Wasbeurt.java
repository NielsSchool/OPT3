import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Wasbeurt extends Subject {
    private int bonCode;
    private Timestamp eindTijd;
    private Wasmachine wasmachine;
    private Wasprogramma wasprogramma;
    private static ArrayList<Wasbeurt> wasbeurten = new ArrayList<>();
    private List<Observer> observers = new ArrayList<>();

    public Wasbeurt(Wasmachine wasmachine, Wasprogramma wasprogramma) {
        this.bonCode = genereerBonCode();
        this.wasmachine = wasmachine;
        this.wasprogramma = wasprogramma;
        this.eindTijd = Timestamp.valueOf(LocalDateTime.now().plus(wasprogramma.getAantalMinuten(), ChronoUnit.MINUTES));
        wasbeurten.add(this);
        startUpdateCycles();
    }
    public boolean checkWachttijdOver() {
        Timestamp huidigeTijd = new Timestamp(System.currentTimeMillis());
        long differenceInMillis = this.eindTijd.getTime() - huidigeTijd.getTime();
        long differenceInMinutes = TimeUnit.MILLISECONDS.toMinutes(differenceInMillis);
        return (differenceInMinutes == 0);
    }
    private int genereerBonCode() {
        Random random = new Random();
        return random.nextInt(90000000) + 10000000;
    }

    public int getBonCode() {
        return bonCode;
    }

    public static void printHuidigeWasbeurten() {
        int index = 0;
        for (Wasbeurt wasbeurt : wasbeurten) {
            index += 1;
            System.out.println("[" + index + "]\nWasmachine: " + wasbeurt.wasmachine.getLocatie() +
                    "\nWasprogramma: " + wasbeurt.wasprogramma.getNaam() + "\nKlaar: " + wasbeurt.checkWachttijdOver());
        }
    }

    public static int getWasbeurtenLength() {
        return wasbeurten.size();
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

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    private void startUpdateCycles() {
        new Thread(() -> {
            try {
                checkWasbeurtKlaar();
                notifyObservers();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    } //wat chatGPT hulp gehad om te kijken wanneer de notifyObserver aangeroepen moet worden. Was aan het proberen om mijn methode checkWachttijd te gebruiken maar het werd te ingewikkeld. ik begrijp wel wat er gebeurd in de code

    public void checkWasbeurtKlaar() throws InterruptedException {
        long delay = this.eindTijd.getTime() - System.currentTimeMillis();
        if (delay > 0) {
            Thread.sleep(delay);
        }
    }
}
