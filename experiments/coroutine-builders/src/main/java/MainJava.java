import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class MainJava {

    public static int N_DONKEYS = 1_000_000;
    private static final List<DonkeyRecord> donkeySpeciesWithRecords = List.of(
            new DonkeyRecord(4L, "Incinnakey"),
            new DonkeyRecord(3L, "Wallacy"),
            new DonkeyRecord(2L, "Analonkey"),
            new DonkeyRecord(1L, "Reetcoil"),
            new DonkeyRecord(0L, "Cocoloco"));

    public static void main(String[] args) {
        var startTimeStamp = Instant.now();
        var listVTs = new ArrayList<Thread>();
        for (int i = 0; i < N_DONKEYS; i++) {
            listVTs.add(Thread.startVirtualThread(() -> {
                var donkey = donkeySpeciesWithRecords.get((int) (Math.random() * donkeySpeciesWithRecords.size()));
                new DonkeyRecord(donkey.id(), donkey.name());
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }));
        }
        listVTs.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("It took %d milliseconds to a give baloney sandwich to everybody after watching the release of %d Record Donkeys".formatted(
                Duration.between(startTimeStamp, Instant.now()).toMillis(), N_DONKEYS));
    }
}
