/**
 * Constant values to be used across Dish and Menu classes.
 *
 * Deliberately uses String constructors to force new objects to be created.
 *
 * @author Austyn Mitchev, Molly Palko, Brooke Chenoweth
 */
public class FoodConstants {
    /** used for uninitialized name */
    public static final String UNKNOWN_NAME = new String("UNKNOWN NAME");

    /** used for uninitialized price */
    public static final double UNKNOWN_PRICE = -543.21;

    // food groups (similar to) USDA myPyramid/USDA myPlate
    public static final String MEAT = new String("MEAT");
    public static final String VEGGIE = new String("VEGETABLE");
    public static final String DAIRY = new String("DAIRY");
    public static final String NUTS = new String("NUTS");
    public static final String GRAIN = new String("GRAINS");

    /** Characters for the known food groups */
    public static final char[] FOOD_GROUP_CHARS =
            new char[]{'m', 'v', 'd', 'n', 'g'};
    /** Strings for known food groups */
    public static final String[] FOOD_GROUP_STRS =
            new String[]{MEAT, VEGGIE, DAIRY, NUTS, GRAIN};

    // menu section (main dish, appetizer, dessert)
    public static final String MAIN = new String("MAIN");
    public static final String SOUP = new String("SOUP");
    public static final String SIDE = new String("SIDE");
    public static final String DESSERT = new String("DESSERT");

    /** used for uninitialized menu section */
    public static final String UNKNOWN_SECTION = new String("UNKNOWN SECTION");
}
