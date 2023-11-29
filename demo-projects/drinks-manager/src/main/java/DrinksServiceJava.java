import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

public class DrinksServiceJava<DRINK extends Drink, BOX extends Box> {

    private final Map<UUID, DRINK> database = new HashMap<>();

    public void sendDrink(DRINK drink) {
        database.put(UUID.randomUUID(), drink);
    }

    @SuppressWarnings("unused")
    public BOX getBox(Function<DRINK,BOX> drinks) {
        return drinks.apply(database.remove(database.keySet().stream().findFirst().orElseThrow()));
    }
}
