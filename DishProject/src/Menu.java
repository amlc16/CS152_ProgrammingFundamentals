/**
 * A Menu lists all the dishes a restaurant can make for a customer, the price
 * of each dish, and diet flags (ex. vegetarian, nut free, etc).
 *
 * Dishes are grouped into menu categories (main, soup, side, dessert).
 *
 * @author Austyn Mitchev, Molly Palko, Brooke Chenoweth
 */
public class Menu {
    /** Name of the menu */
    private String name;

    /**
     * Array of menu section names
     */
    private String[] sections;

    /** Number of dishes currently in each section */
    private int[] numDishes;

    /**
     * each row holds a different section of the menu
     * each row can have a different number of columns
     */
    private Dish[][] dishes;


    /**
     * create an empty menu to fill with dishes in later:
     * # rows in menu = sections.length
     * # cols in menu[i] = numDishes[i]
     * the number of dishes (col) in each section (row) can be different
     *
     * @param name name of menu
     * @param sections fixed order and amount of menu sections
     * @param capacity determines max number of dishes in each section of
     *                 the menu
     */
    public Menu (String name, String[] sections, int[] capacity) {
        this.name = name;
        this.sections = sections;
        this.numDishes = new int[capacity.length];
        dishes = new Dish[sections.length][];
        for (int i = 0; i < sections.length; i++) {
            // section i could hold up to capacity[i] dishes
            // but initially has none
            dishes[i] = new Dish[capacity[i]];
            numDishes[i] = 0;
        }
    }

    /**
     * Gets the size of the menu
     * @return The number of dishes stored in this menu object.
     */
    public int getMenuSize(){
        int size = 0;
        for (int i = 0; i < sections.length; i++) {
            size += numDishes[i];
        }
        return size;
    }

    /**
     * Get the menu name.
     * @return Name of the menu.
     */
    public String getName(){
        return name;
    }

    /**
     * Set the menu name. May not be empty.
     * @param name New menu name.
     */
    public void setName(String name) {
        if (!name.isEmpty()) {
            this.name = name;
        }
    }

    /**
     * Add a dish to the menu.
     * If the dish is already present, does not add duplicate.
     * If the dish is new, add it after the existing dishes in its section
     * @param d Dish to add.
     * @return String describing what happened
     * - "Section not found!" : If the section for this dish is not on the menu
     * - "Dish already exists!" : If the dish already exists on the menu
     * - "No room on the menu!" : If there is no room on the menu for the dish
     * - "Dish added!" : If the dish was successfully added
     */
    public String addDish(Dish d) {
        int indexFoodSection = -1;
        for (int i = 0; i < sections.length; i++) {
            if (sections[i].equals(d.getMenuSection())) {
                indexFoodSection = i;
                break;
            }
        }
        if (indexFoodSection == -1) {
            return "Section not found!";
        }

        for (int i = 0; i < numDishes[indexFoodSection]; i++) {
            if (dishes[indexFoodSection][i].isSame(d)) {
                return "Dish already exists!";
            }
        }

        if (numDishes[indexFoodSection] < dishes[indexFoodSection].length) {
            dishes[indexFoodSection][numDishes[indexFoodSection]] = d;
            numDishes[indexFoodSection]++;
            return "Dish added!";
        } else {
            return "No room on the menu!";
        }
    }

    /**
     * Add all the dishes in the array to the menu.
     * Should use addDish() to add each one.
     *
     * @param newDishes Dishes to add.
     * @return Array of strings returned by the calls to addDish
     */
    public String[] addDishes(Dish[] newDishes) {
        String[] results = new String[newDishes.length];
        for (int i = 0; i < newDishes.length; i++) {
            results[i] = addDish(newDishes[i]);
        }
        return results;
    }

    /**
     * Get a 1 dimensional array of all the dishes on the menu.
     * Dishes are ordered section by section (row by row)
     *
     * @return Array of all dishes.
     */
    public Dish[] getDishes() {
        int totalDishes = getMenuSize();
        Dish[] allDishes = new Dish[totalDishes];
        int index = 0;
        for (int i = 0; i < sections.length; i++) {
            for (int j = 0; j < numDishes[i]; j++) {
                allDishes[index] = dishes[i][j];
                index++;
            }
        }
        return allDishes;
    }

    /**
     * Get string representation of the menu.
     * Should be menu name on first line,
     * followed by one dish per line using Dish.toString() format
     * @return String of all dishes on the menu
     */
    public String toString() {
        String menu = name + "\n";
        for (int i = 0; i < sections.length; i++) {
            for (int j = 0; j < numDishes[i]; j++) {
                menu += dishes[i][j].toString() + "\n";
            }
        }
        return menu;
    }

    /**
     * Create string of entire menu, grouped by menu section
     * @return String of menu sorted by category in the following format:
     * "*** <Menu Name> ***
     *  <Section 0>:
     *  - <Dish 0> (in the Dish.toMenuString() format)
     *  - <Dish 1>
     *  ...
     *  - <Dish n>
     *  <Section 1>:
     *  - <Dish 0> (in the Dish.toMenuString() format)
     *  - <Dish 1>
     *   ...
     *  - <Dish n>
     *  ..."
     */
    public String getFullMenu() {
        String fullMenu = "*** " + name + " ***\n";
        for (int i = 0; i < sections.length; i++) {
            fullMenu += sections[i] + ":\n";
            for (int j = 0; j < numDishes[i]; j++) {
                fullMenu += "- " + dishes[i][j].toMenuString() + "\n";
            }
        }
        return fullMenu;
    }

    /**
     * Create string of only the vegetarian dishes on the menu in the same
     * format
     * as getFullMenu, but precede vegan dishes with a * instead of a -
     * @return String of vegetarian dishes
     */
    public String getVegetarianMenu() {
        String vegetarianMenu = "*** " + name + " ***\n";
        for (int i = 0; i < sections.length; i++) {
            vegetarianMenu += sections[i] + ":\n";
            for (int j = 0; j < numDishes[i]; j++) {
                if (dishes[i][j].isVegetarian()) {
                    if (dishes[i][j].isVegan()) {
                        vegetarianMenu += "* ";
                    } else{
                        vegetarianMenu += "- ";
                    }
                    vegetarianMenu += dishes[i][j].toMenuString() + "\n";
                }
            }
        }
        return vegetarianMenu;
    }

    /**
     * Deletes dish entirely from the menu.
     * The original size of the array should be maintained and
     * it should be altered such that 'Dishes' ahead of the
     * removed 'Dish' are moved ahead (i.e if removed dish at index [i][j],
     * then after removal menu[i][j] = menu[i][j+ 1],
     * menu[i][j+1] = menu[i][j+2],
     * and the last element menu[i][menu[i].length-1] = null
     * Extra Credit
     * @param d Dish to remove.
     * @return String denoting success or failure.
     * * MAKE SURE TO UPDATE OPTIONS AFTER REMOVING
     */
    public boolean removeDish(Dish d) {
        int indexMenuSection = -1;
        for (int i = 0; i < sections.length; i++) {
            if (sections[i].equals(d.getMenuSection())) {
                indexMenuSection = i;
                break;
            }
        }

        for (int i = 0; i < numDishes[indexMenuSection]; i++) {
            if (dishes[indexMenuSection][i].isSame(d)) {
                for (int j = i; j < numDishes[indexMenuSection] - 1; j++) {
                    dishes[indexMenuSection][j] =
                            dishes[indexMenuSection][j + 1];
                }
                dishes[indexMenuSection][numDishes[indexMenuSection] - 1]
                        = null;
                numDishes[indexMenuSection]--;
                return true;
            }
        }
        return false;
    }
}
