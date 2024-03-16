/**
 * A Dish represents an item served in a restaurant. A dish has a name, a price, a menu
 * section, and some food group classifications.
 *
 * @author Alexander Leon
 */
public class Dish {
    private String name;
    private double price;
    private String menuSection;
    private String foodGroups;


    /**
     * The default constructor with no parameters that creates a new Dish
     * with unknown name, price, and menu section and with empty food groups.
     */
    public Dish () {
        this.name = FoodConstants.UNKNOWN_NAME;
        this.price = FoodConstants.UNKNOWN_PRICE;
        this.menuSection = FoodConstants.UNKNOWN_SECTION;
        this.foodGroups = "";
    }

    /**
     * Constructor that creates a new Dish with the given menu section,
     * unknown name and price, and with empty food groups.
     * @param menuSection dish’s menu section
     */
    public Dish (String menuSection) {
        this.name = FoodConstants.UNKNOWN_NAME;
        this.price = FoodConstants.UNKNOWN_PRICE;
        this.menuSection = menuSection;
        this.foodGroups = "";
    }

    /**
     * Constructor that creates a new Dish with all member variables initialized
     * with the values given.
     * @param name name of dish
     * @param price dish's price
     * @param menuSection dish’s menu section
     * @param foodGroups dish’s food group
     */

    public Dish (String name, double price, String menuSection,
                 String foodGroups) {
        this.name = name;
        this.price = (price >= 0) ? price : FoodConstants.UNKNOWN_PRICE;
        this.menuSection = menuSection;
        this.foodGroups = foodGroups;
    }

    /**
     * Sets the dish’s name
     * @param name new dish's name, must not be empty.
     */
    public void setName (String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Get the dish name
     * @return dish’s name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the dish’s price. The price must not be negative.
     * @param price new dish’s price
     */
    public void setPrice(double price){
        if (price >= 0) {
            this.price = price;
        }
    }

    /**
     * Get the dish price
     * @return double value of dish’s price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Set the dish’s menu section.
     * @param section new dish’s menu section
     */
    public void setMenuSection(String section){
        this.menuSection = section;
    }

    /**
     * Get the dish menu section
     * @return string representing the dish’s menu section
     */
    public String getMenuSection() {
        return menuSection;
    }

    /**
     * Set the dish’s food groups string. It only includes the known food group
     * characters given in the FoodConstants class, and ignores any other
     * unknown characters. It also ignores any duplicate characters.
     * So, if we call this method with an argument of “mdvdxmq”, the food
     * groups string should be set to “mdv”.
     * @param foodGroups String representing known food group characters
     */
    public void setFoodGroups (String foodGroups) {
        char[] knownFoodGroups = FoodConstants.FOOD_GROUP_CHARS;
        boolean[] added = new boolean[knownFoodGroups.length];
        this.foodGroups = "";
        for (char c : foodGroups.toCharArray()) {
            for (int i = 0; i < knownFoodGroups.length; i++) {
                if (c == knownFoodGroups[i] && !added[i]) {
                    added[i] = true;
                    this.foodGroups += c;
                    break;
                }
            }
        }
    }

    /**
     * Get the dishes food groups
     * @return String representing the dishes food groups
     */
    public String getFoodGroups() {
        return foodGroups;
    }


    /**
     * Get string representation of the menu.
     * It uses the following format:
     * -If food groups are empty and price is unknown:
     * Carrot Cake, DESSERT
     * –If food groups are empty and price is known:
     * Carrot Cake, DESSERT: $9.99
     * –If food groups are given and price is unknown:
     * Carrot Cake (nvdg), DESSERT
     * –If all fields are known:
     * Carrot Cake (nvdg), DESSERT: $9.99
     *
     * @return String of a dish
     */
    public String toString () {
        String result = name;
        if (!foodGroups.isEmpty()) {
            result += " (" + foodGroups + ")";
        }
        result += ", " + menuSection;
        if (price != FoodConstants.UNKNOWN_PRICE) {
            result += ": $" + price;
        }
        return result;
    }

    /**
     * Get a fancier string representation of the dish.
     * The menu section is not included in this string, since it is assumed
     * this string will be printed in the appropriate menu section already.
     * Food groups (if known) are listed on a second line indented with five
     * spaces and using the words defined in the FoodConstants class, rather
     * than just the character abbreviations stored in the food groups member
     * variable string.
     * @return Fancier string representation of the dish
     */
    public String toMenuString() {
        String menuString = this.getName();

        if (this.getPrice() != FoodConstants.UNKNOWN_PRICE) {

            menuString += ": $" + this.getPrice();
        }

        if (!this.getFoodGroups().isEmpty()) {

            menuString += "\n     ";

            for (char foodGroupChar : this.getFoodGroups().toCharArray()) {
                for (int i = 0; i < FoodConstants.FOOD_GROUP_CHARS.length; i++){
                    if (foodGroupChar == FoodConstants.FOOD_GROUP_CHARS[i]) {
                        menuString += FoodConstants.FOOD_GROUP_STRS[i] + ", ";
                    }
                }
            }

            menuString = menuString.substring(0, menuString.length() - 2);
        }
        return menuString;
    }

    /**
     * A dish is vegetarian if its food groups do not contain meat.
     * @return true if the dish vegetarian, false otherwise.
     */
    public boolean isVegetarian() {
        String foodGroups = this.getFoodGroups();
        return !foodGroups.contains("m");
    }

    /**
     * A dish is vegan if its food groups do not contain meat or dairy.
     * @return true if the dish vegan, false otherwise
     */
    public boolean isVegan() {
        String foodGroups = this.getFoodGroups();
        return !foodGroups.contains("m") && !foodGroups.contains("d");
    }

    /**
     * Two dishes are the same if they both have unknown names, or if they have
     * the same name, price, menu section, and food groups (in any order).
     * @param other dish to compare to
     * @return true if the two dishes are the same, false if not
     */
    public boolean isSame(Dish other) {

        boolean bothUnknownNames =
                this.getName().equals(FoodConstants.UNKNOWN_NAME)
                && other.getName().equals(FoodConstants.UNKNOWN_NAME);

        boolean same = this.getName().equals(other.getName())
                && this.getPrice() == other.getPrice()
                && this.getMenuSection().equals(other.getMenuSection());

        return bothUnknownNames || same;
    }
}
