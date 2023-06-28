import java.util.ArrayList;
import java.util.List;

public class MainJava {
    private static List<DonkeyRecord> donkeySpeciesWithRecords = List.of(
            new DonkeyRecord(4L, "Incinnakey"),
            new DonkeyRecord(3L, "Wallacy"),
            new DonkeyRecord(2L, "Analonkey"),
            new DonkeyRecord(1L, "Reetcoil"),
            new DonkeyRecord(0L, "Cocoloco"));
    public static void main(String[] args) {
        var listVTs = new ArrayList<Thread>();
        for (int i = 0; i < 1000000; i++) {
            listVTs.add(Thread.startVirtualThread(() -> {
                var donkey = donkeySpeciesWithRecords.get((int)(Math.random()*donkeySpeciesWithRecords.size()));
                new DonkeyRecord(donkey.id(), donkey.name());
            }));
        }
        listVTs.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
