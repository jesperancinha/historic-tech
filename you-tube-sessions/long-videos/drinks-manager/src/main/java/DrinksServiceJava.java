import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class DrinksServiceJava<DRINK extends Drink, BOX extends Box> {

    private final Map<UUID, DRINK> database = new HashMap<>();

    public void sendDrink(DRINK drink) {
        database.put(UUID.randomUUID(), drink);
    }

    public void getBox(Consumer<DRINK> o) {
    }
}
