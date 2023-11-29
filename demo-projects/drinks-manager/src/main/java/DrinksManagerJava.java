

public class DrinksManagerJava {
    public static void checkResistenceState(DrinksServiceJava<? super WarmDrink, ? extends Box> drinksService) {
    }

    public static void checkCoolerState(DrinksServiceJava<? super ColdDrink, ? extends Box> drinksService) {
    }

    public static void main(String[] args) {
        var coldDrinksService = new DrinksServiceJava<ColdDrink, FamilyBox>();
        coldDrinksService.sendDrink(new Lemonade());
        coldDrinksService.sendDrink(new OrangeJuice());
        coldDrinksService.getBox(FamilyBox::new);
        coldDrinksService.getBox(FamilyBox::new);

        var warmeDrinksService = new DrinksServiceJava<WarmDrink, DeluxeBox>();
        warmeDrinksService.sendDrink(new CoffeeDrink());
        warmeDrinksService.sendDrink(new TeaDrink());
        warmeDrinksService.getBox(DeluxeBox::new);
        warmeDrinksService.getBox(DeluxeBox::new);

        var genericDrinksService = new DrinksServiceJava<Drink, CardboardBox>();

        checkResistenceState(warmeDrinksService);
        checkCoolerState(coldDrinksService);
        checkResistenceState(genericDrinksService);
        checkCoolerState(genericDrinksService);
    }
}
