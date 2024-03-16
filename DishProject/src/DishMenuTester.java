import java.util.Arrays;
/**
 * dishTester() and menuTester() can be run separately or out of order
 * NOTE: Menu builds upon Dish so menuTester() won't score correctly when
 *       Dish is incomplete
 *
 * @author Austyn Mitchev, Molly Palko, Brooke Chenoweth
 */
public class DishMenuTester {

    private static void dishTester() {
        int dishScore = 0;
        int tScore = 0;
        /** Test the constructors to make sure they compile and run.*/
        System.out.println("*** TESTING DISH IMPLEMENTATION ***");
        System.out.println("Attempting constructors:");


        Dish d1 = new Dish();
        Dish d2 = new Dish();
        Dish d3 = new Dish(new String("Chicken Alfredo with Broccoli"), 19.99, "MAIN", "mvdng");
        Dish d4 = new Dish("Chicken Caesar Salad", 9.99, "SIDE", "dngmv");
        Dish d5 = new Dish("Clam Chowder", 14.99, "SOUP", "mvd");
        if(d1.getName().equals(FoodConstants.UNKNOWN_NAME) &&
           d2.getName().equals(FoodConstants.UNKNOWN_NAME)){ tScore++;}
        if(d1.getMenuSection().equals(FoodConstants.UNKNOWN_SECTION) &&
                d2.getMenuSection().equals(FoodConstants.UNKNOWN_SECTION)){ tScore++;}
        if(d3.getMenuSection().equals(FoodConstants.MAIN)){ tScore++;}
        if(d4.getMenuSection().equals(FoodConstants.SIDE)){ tScore++;}
        if(d5.getMenuSection().equals(FoodConstants.SOUP)){ tScore++;}

        // test additional constructor
        Dish d6 = new Dish("SOUP");
        if(d6.getMenuSection().equals(FoodConstants.SOUP)) { tScore++; }
        Dish d7 = new Dish("DESSERT");
        if(d7.getMenuSection().equals(FoodConstants.DESSERT)){ tScore++; }

        System.out.println("  - Constructors seem " +
                "functional: " + tScore + "/7");
        dishScore += tScore;
        tScore = 0;

        // NOTE: Just because constructor tests pass, it doesn't mean they
        //       are really correct. It's just that incorrect initialization
        //       may not be obvious until we test getters and setters

        if(d1.toString().equals("UNKNOWN NAME, UNKNOWN SECTION")) { tScore++; }
        if(d6.toString().equals("UNKNOWN NAME, SOUP")) { tScore++; }
        System.out.println("  - toString before setters: " + tScore + "/2");
        dishScore += tScore;
        tScore = 0;

        if(d1.isSame(d6)) { tScore++; }
        if(!d1.isSame(d3)) { tScore++; }
        System.out.println("  - isSame before setters: " + tScore + "/2");
        dishScore += tScore;
        tScore = 0;


        /** Setters and Getters */
        System.out.println("Attempting setters and getters:");

        //Set & Get name tests
        if(d1.getName().equals("UNKNOWN NAME")){ tScore++;}
        d1.setName(new String("Chicken Alfredo with Broccoli"));
        if(d1.getName().equals("Chicken Alfredo with Broccoli")){ tScore++;}

        d2.setName("Carrot Cake");
        if(d2.getName().equals("Carrot Cake")){ tScore++;}
        d2.setName("");
        if(d2.getName().equals("Carrot Cake")){ tScore++;}

        System.out.println("  - setName & getName: " + tScore + "/4");
        dishScore += tScore;
        tScore = 0;

        //Set & Get menu section tests
        d1.setMenuSection(FoodConstants.MAIN);
        if(d1.getMenuSection().equals(FoodConstants.MAIN)){ tScore++;}
        d2.setMenuSection(FoodConstants.DESSERT);
        if(d2.getMenuSection().equals(FoodConstants.DESSERT)){ tScore++;}
        System.out.println("  - setMenuSection & getMenuSection: " + tScore + "/2");
        dishScore += tScore;
        tScore = 0;

        //Set & Get food group tests
        if(d1.getFoodGroups().equals("")) { tScore++;}
        d1.setFoodGroups("mdvgnmdxqdv");
        if(d1.getFoodGroups().equals("mdvgn")) { tScore++;}
        d2.setFoodGroups("nvdg");
        if(d2.getFoodGroups().equals("nvdg")) { tScore++;}
        d2.setFoodGroups("anbvndddcg");
        if(d2.getFoodGroups().equals("nvdg")) { tScore++;}
        System.out.println("  - setFoodGroups & getFoodGroups: " + tScore + "/4");
        dishScore += tScore;
        tScore = 0;

        //Set & Get price tests
        if(d1.getPrice() == FoodConstants.UNKNOWN_PRICE){ tScore++;}
        d1.setPrice(19.99);
        if(d1.getPrice() == 19.99){ tScore++;}
        d2.setPrice(9.99);
        if(d2.getPrice() == 9.99){ tScore++;}
        d2.setPrice(-5.43);
        if(d2.getPrice() == 9.99){ tScore++;}
        System.out.println("  - setPrice & getPrice: " + tScore + "/4");
        dishScore += tScore;
        tScore = 0;

        /** Other Methods */
        System.out.println("Attempting other methods:");
        //toString tests
        if(d1.toString().equals("Chicken Alfredo with Broccoli (mdvgn), MAIN: $19.99")){ tScore++;}
        if(d2.toString().equals("Carrot Cake (nvdg), DESSERT: $9.99")){ tScore++;}
        if(d3.toString().equals("Chicken Alfredo with Broccoli (mvdng), MAIN: $19.99")){ tScore++;}
        if(d4.toString().equals("Chicken Caesar Salad (dngmv), SIDE: $9.99")){ tScore++;}
        if(d5.toString().equals("Clam Chowder (mvd), SOUP: $14.99")){ tScore++;}
        System.out.println("  - toString: " + tScore + "/5");
        dishScore += tScore;
        tScore = 0;

        //toMenuString tests
        if(d1.toMenuString().equals("Chicken Alfredo with Broccoli: $19.99\n" +
                                    "     MEAT, DAIRY, VEGETABLE, GRAINS, NUTS")){ tScore++;}
        if(d2.toMenuString().equals("Carrot Cake: $9.99\n" +
                                    "     NUTS, VEGETABLE, DAIRY, GRAINS")){ tScore++;}
        if(d3.toMenuString().equals("Chicken Alfredo with Broccoli: $19.99\n" +
                                    "     MEAT, VEGETABLE, DAIRY, NUTS, GRAINS")){ tScore++;}
        if(d4.toMenuString().equals("Chicken Caesar Salad: $9.99\n" +
                                    "     DAIRY, NUTS, GRAINS, MEAT, VEGETABLE")){ tScore++;}
        if(d5.toMenuString().equals("Clam Chowder: $14.99\n" +
                                    "     MEAT, VEGETABLE, DAIRY")){ tScore++;}
        System.out.println("  - toMenuString: " + tScore + "/5");
        dishScore += tScore;
        tScore = 0;

        //isSame tests
        if(d1.isSame(d3)){ tScore++;}
        if(!d2.isSame(d3)){ tScore++;}
        System.out.println("  - isSame: " + tScore + "/2");
        dishScore += tScore;
        tScore = 0;

        //isVegetarian tests
        if(d2.isVegetarian()){ tScore += 2;}
        if(!d4.isVegetarian()){ tScore += 2;}
        System.out.println("  - isVegetarian: " + tScore + "/4");
        dishScore += tScore;
        tScore = 0;

        //isVegan tests
        if(!d2.isVegan()){ tScore += 2;}
        d2.setFoodGroups("nvg");
        if(d2.isVegan()){ tScore += 2;}
        System.out.println("  - isVegan: " + tScore + "/4");
        dishScore += tScore;
        tScore = 0;

        System.out.println("Dish implementation score: " + dishScore + "/45");
    }

    private static void menuTester() {
        int menuScore = 0;
        int tScore = 0;

        System.out.println("*** TESTING MENU IMPLEMENTATION ***");

        Dish d1 = new Dish("Pad See Ew", 16.99, "MAIN", "mvg");
        Dish d2 = new Dish("Massaman Cury", 15.99, "MAIN", "vgn");
        Dish d3 = new Dish("Chicken Alfredo w/ Broccoli", 19.99, "MAIN", "mvdng");
        Dish d4 = new Dish("Chicken Caesar Salad", 9.99, "SIDE", "dngmv");
        Dish d5 = new Dish("Clam Chowder", 14.99, "SOUP", "mvd");

        Dish d6 = new Dish("Vegetable and Nut Pilaf", 14.99, FoodConstants.MAIN, "nvdg");
        Dish d7 = new Dish("Cashew Chicken and Rice", 17.99, FoodConstants.MAIN, "dngmv");
        Dish d8 = new Dish("Pad Thai", 17.99, FoodConstants.MAIN, "nvdmg");
        Dish d9 = new Dish("Beef Stroganoff", 16.99, FoodConstants.MAIN, "mvd");
        Dish d10 = new Dish("Vegetable Fried Rice", 8.99, FoodConstants.SIDE, "vgd");
        Dish d11 = new Dish("Winter Salad", 7.99, FoodConstants.SIDE, "vgn");
        Dish d12 = new Dish("Southwest Salad", 8.99, FoodConstants.SIDE, "nvdmg");
        Dish d13 = new Dish("Mamas Chicken and Noodle Soup", 8.99, FoodConstants.SOUP, "mvg");
        Dish d14 = new Dish("Beef Stew", 8.99, FoodConstants.SOUP, "mvd");
        Dish d15 = new Dish("Vegetable Stew", 7.99, FoodConstants.SOUP, "vgd");
        Dish d16 = new Dish("Rice Pudding with Almonds", 7.99, FoodConstants.DESSERT, "nvdg");
        Dish d17 = new Dish("Pumpkin Pie with Pecans", 8.99, FoodConstants.DESSERT, "nvdg");
        Dish d18 = new Dish("Carrot Cake", 9.99, FoodConstants.DESSERT, "vgd");

        Dish[] emptyDishes = new Dish[0];

        Dish[] menu1Dishes = {d8, d7, d6, d5, d14, d15, d11, d10};

        Dish[] menu2Dishes1 = {d3, d4, d18, d6, d11, d16, d7, d9, d12, d17};
        Dish[] menu2Dishes2 = {d3, d6, d7, d9, d4, d11, d12, d18, d16, d17};

        Dish[] menu3Dishes = {d3, d6, d7, d8,
                              d9, d5, d13, d14, d15,
                              d4, d11, d12,
                              d18, d16, d17};
        Dish[] menu3DishesAfterRemove = {d3, d6, d7, d8,
                                         d9, d5, d13, d14, d15,
                                         d4, d11,
                                         d18, d16, d17};


        /** Constructors */
        System.out.println("Attempting constructors:");
        //Constructor tests
        Menu menu1 = new Menu("Summer Menu",
                              new String[]{"MAIN", "SOUP", "SIDE"},
                              new int[]{4,4,3});
        if(menu1.getFullMenu().equals("*** Summer Menu ***\n" +
                                      "MAIN:\n" +
                                      "SOUP:\n" +
                                      "SIDE:\n")) { tScore++; }
        Menu menu2 = new Menu("Funky Dunky Menu",
                              new String[]{"MAIN", "SIDE", "DESSERT"},
                              new int[]{5,3,3});
        if(menu2.getName().equals("Funky Dunky Menu")) { tScore++;}
        menu2.addDishes(menu2Dishes1);
        Menu menu3 = new Menu("Spring Menu",
                              new String[]{"MAIN", "SOUP", "SIDE", "DESSERT"},
                              new int[] {5, 4, 3, 3});
        if(menu3.toString().equals("Spring Menu\n")) { tScore++; }
        menu3.addDishes(menu3Dishes);
        System.out.println("  - Constructors seem " +
                "functional: " + tScore + "/3");
        menuScore += tScore;
        tScore = 0;

        // NOTE: Just because constructor tests pass, it doesn't mean they
        //       are really correct. It's just that incorrect initialization
        //       may not be obvious until we test getters and setters


        /** Setters and Getters */
        System.out.println("Attempting setters and getters:");
        // Get options tests

        if(menu1.getMenuSize() == 0){ tScore++;}
        if(menu3.getMenuSize() == 15){ tScore++;}
        System.out.println("  - getMenuSize: " + tScore + "/2");
        menuScore += tScore;
        tScore = 0;

        
        if(Arrays.equals(menu1.getDishes(), emptyDishes)) { tScore++; }
        if(Arrays.equals(menu2.getDishes(), menu2Dishes2)){ tScore++;}
        if(Arrays.equals(menu3.getDishes(), menu3Dishes)){ tScore++;}
        System.out.println("  - getDishes: " + tScore + "/3");
        menuScore += tScore;
        tScore = 0;
        
        // Set and get name tests
        menu2.setName("Winter Menu");
        if(menu2.getName().equals("Winter Menu")){ tScore++;}
        menu2.setName("");
        if(menu2.getName().equals("Winter Menu")){ tScore++;}
        System.out.println("  - setName and getName: " + tScore + "/2");
        menuScore += tScore;
        tScore = 0;

        // Set and get menu tests
        menu1.addDishes(menu1Dishes);
        if(Arrays.equals(menu1.getDishes(), menu1Dishes)) { tScore++; };
        if(menu1.getMenuSize() ==  menu1Dishes.length) { tScore++; };
        System.out.println("  - adding to empty menu: " + tScore + "/2");
        menuScore += tScore;
        tScore = 0;

        /** Other Methods */
        System.out.println("Attempting other methods:");

        // addDish tests
        if(menu1.addDish(d1).equals("Dish added!")){ tScore++;}
        else System.out.println("Failed add-1");
        if(menu1.addDish(d9).equals("No room on the menu!")){ tScore++;}
        else System.out.println("Failed add-2");
        if(menu2.addDish(d8).equals("Dish added!")){ tScore++;}
        else System.out.println("Failed add-3");
        System.out.println("  - addDish: " + tScore + "/3");
        menuScore += tScore;
        tScore = 0;

        // addDishes tests
        if(Arrays.equals(menu1.addDishes(new Dish[]{d7, d8, d12}),
                         new String[]{"Dish already exists!",
                                      "Dish already exists!",
                                      "Dish added!"})) { tScore++; }
        else System.out.println("Failed addDishes-1");

        if(Arrays.equals(menu2.addDishes(new Dish[]{d10, d8, d13}),
                         new String[]{"No room on the menu!",
                                      "Dish already exists!",
                                      "Section not found!"})) { tScore++; }
        else System.out.println("Failed addDishes-3");
        System.out.println("  - addDishes: " + tScore + "/2");
        menuScore += tScore;
        tScore = 0;


        // getFullMenu tests
        String s1 = menu1.getFullMenu();
        if(s1.equals("*** Summer Menu ***\n" +
                     "MAIN:\n" +
                     "- Pad Thai: $17.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, MEAT, GRAINS\n" +
                     "- Cashew Chicken and Rice: $17.99\n" +
                     "     DAIRY, NUTS, GRAINS, MEAT, VEGETABLE\n" +
                     "- Vegetable and Nut Pilaf: $14.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Pad See Ew: $16.99\n" +
                     "     MEAT, VEGETABLE, GRAINS\n" +
                     "SOUP:\n" +
                     "- Clam Chowder: $14.99\n" +
                     "     MEAT, VEGETABLE, DAIRY\n" +
                     "- Beef Stew: $8.99\n" +
                     "     MEAT, VEGETABLE, DAIRY\n" +
                     "- Vegetable Stew: $7.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "SIDE:\n" +
                     "- Winter Salad: $7.99\n" +
                     "     VEGETABLE, GRAINS, NUTS\n" +
                     "- Vegetable Fried Rice: $8.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "- Southwest Salad: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, MEAT, GRAINS\n")){ tScore += 2;}
        else System.out.println("Failed s1: \n" + s1);

        String s2 = menu2.getFullMenu();
        if(s2.equals("*** Winter Menu ***\n" +
                     "MAIN:\n" +
                     "- Chicken Alfredo w/ Broccoli: $19.99\n" +
                     "     MEAT, VEGETABLE, DAIRY, NUTS, GRAINS\n" +
                     "- Vegetable and Nut Pilaf: $14.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Cashew Chicken and Rice: $17.99\n" +
                     "     DAIRY, NUTS, GRAINS, MEAT, VEGETABLE\n" +
                     "- Beef Stroganoff: $16.99\n" +
                     "     MEAT, VEGETABLE, DAIRY\n" +
                     "- Pad Thai: $17.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, MEAT, GRAINS\n" +
                     "SIDE:\n" +
                     "- Chicken Caesar Salad: $9.99\n" +
                     "     DAIRY, NUTS, GRAINS, MEAT, VEGETABLE\n" +
                     "- Winter Salad: $7.99\n" +
                     "     VEGETABLE, GRAINS, NUTS\n" +
                     "- Southwest Salad: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, MEAT, GRAINS\n" +
                     "DESSERT:\n" +
                     "- Carrot Cake: $9.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "- Rice Pudding with Almonds: $7.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Pumpkin Pie with Pecans: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n")){ tScore += 2;}
        else System.out.println("Failed s2: \n" + s2);

        String s3 = menu3.getFullMenu();
        if(s3.equals("*** Spring Menu ***\n" +
                     "MAIN:\n" +
                     "- Chicken Alfredo w/ Broccoli: $19.99\n" +
                     "     MEAT, VEGETABLE, DAIRY, NUTS, GRAINS\n" +
                     "- Vegetable and Nut Pilaf: $14.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Cashew Chicken and Rice: $17.99\n" +
                     "     DAIRY, NUTS, GRAINS, MEAT, VEGETABLE\n" +
                     "- Pad Thai: $17.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, MEAT, GRAINS\n" +
                     "- Beef Stroganoff: $16.99\n" +
                     "     MEAT, VEGETABLE, DAIRY\n" +
                     "SOUP:\n" +
                     "- Clam Chowder: $14.99\n" +
                     "     MEAT, VEGETABLE, DAIRY\n" +
                     "- Mamas Chicken and Noodle Soup: $8.99\n" +
                     "     MEAT, VEGETABLE, GRAINS\n" +
                     "- Beef Stew: $8.99\n" +
                     "     MEAT, VEGETABLE, DAIRY\n" +
                     "- Vegetable Stew: $7.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "SIDE:\n" +
                     "- Chicken Caesar Salad: $9.99\n" +
                     "     DAIRY, NUTS, GRAINS, MEAT, VEGETABLE\n" +
                     "- Winter Salad: $7.99\n" +
                     "     VEGETABLE, GRAINS, NUTS\n" +
                     "- Southwest Salad: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, MEAT, GRAINS\n" +
                     "DESSERT:\n" +
                     "- Carrot Cake: $9.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "- Rice Pudding with Almonds: $7.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Pumpkin Pie with Pecans: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n")){ tScore += 2;}
        else System.out.println("Failed s3: \n" + s3);
        System.out.println("  - getFullMenu: " + tScore + "/6");
        menuScore += tScore;
        tScore = 0;

        // getVegetarianMenu tests
        String s4 = menu1.getVegetarianMenu();
        if(s4.equals("*** Summer Menu ***\n" +
                     "MAIN:\n" +
                     "- Vegetable and Nut Pilaf: $14.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "SOUP:\n" +
                     "- Vegetable Stew: $7.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "SIDE:\n" +
                     "* Winter Salad: $7.99\n" +
                     "     VEGETABLE, GRAINS, NUTS\n" +
                     "- Vegetable Fried Rice: $8.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n")){ tScore += 2;}
        else System.out.println("Failed s4: \n" + s4);

        String s5 = menu2.getVegetarianMenu();
        if(s5.equals("*** Winter Menu ***\n" +
                     "MAIN:\n" +
                     "- Vegetable and Nut Pilaf: $14.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "SIDE:\n" +
                     "* Winter Salad: $7.99\n" +
                     "     VEGETABLE, GRAINS, NUTS\n" +
                     "DESSERT:\n" +
                     "- Carrot Cake: $9.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "- Rice Pudding with Almonds: $7.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Pumpkin Pie with Pecans: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n")){ tScore += 2;}
        else System.out.println("Failed s5: \n" + s5);

        String s6 = menu3.getVegetarianMenu();
        if(s6.equals("*** Spring Menu ***\n" +
                     "MAIN:\n" +
                     "- Vegetable and Nut Pilaf: $14.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "SOUP:\n" +
                     "- Vegetable Stew: $7.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "SIDE:\n" +
                     "* Winter Salad: $7.99\n" +
                     "     VEGETABLE, GRAINS, NUTS\n" +
                     "DESSERT:\n" +
                     "- Carrot Cake: $9.99\n" +
                     "     VEGETABLE, GRAINS, DAIRY\n" +
                     "- Rice Pudding with Almonds: $7.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n" +
                     "- Pumpkin Pie with Pecans: $8.99\n" +
                     "     NUTS, VEGETABLE, DAIRY, GRAINS\n")){ tScore += 2;}
        else System.out.println("Failed s6: \n" + s6);
        System.out.println("  - getVegetarianMenu: " + tScore + "/6");
        menuScore += tScore;
        tScore = 0;


        // toString tests
        String s11 = menu1.toString();
        if(s11.equals("Summer Menu\n" +
                      "Pad Thai (nvdmg), MAIN: $17.99\n" +
                      "Cashew Chicken and Rice (dngmv), MAIN: $17.99\n" +
                      "Vegetable and Nut Pilaf (nvdg), MAIN: $14.99\n" +
                      "Pad See Ew (mvg), MAIN: $16.99\n" +
                      "Clam Chowder (mvd), SOUP: $14.99\n" +
                      "Beef Stew (mvd), SOUP: $8.99\n" +
                      "Vegetable Stew (vgd), SOUP: $7.99\n" +
                      "Winter Salad (vgn), SIDE: $7.99\n" +
                      "Vegetable Fried Rice (vgd), SIDE: $8.99\n" +
                      "Southwest Salad (nvdmg), SIDE: $8.99\n")){ tScore += 2;}
        else System.out.println("Failed s11: \n" + s11);

        String s22 = menu2.toString();
        if(s22.equals("Winter Menu\n" +
                      "Chicken Alfredo w/ Broccoli (mvdng), MAIN: $19.99\n" +
                      "Vegetable and Nut Pilaf (nvdg), MAIN: $14.99\n" +
                      "Cashew Chicken and Rice (dngmv), MAIN: $17.99\n" +
                      "Beef Stroganoff (mvd), MAIN: $16.99\n" +
                      "Pad Thai (nvdmg), MAIN: $17.99\n" +
                      "Chicken Caesar Salad (dngmv), SIDE: $9.99\n" +
                      "Winter Salad (vgn), SIDE: $7.99\n" +
                      "Southwest Salad (nvdmg), SIDE: $8.99\n" +
                      "Carrot Cake (vgd), DESSERT: $9.99\n" +
                      "Rice Pudding with Almonds (nvdg), DESSERT: $7.99\n" +
                      "Pumpkin Pie with Pecans (nvdg), DESSERT: $8.99\n")){ tScore += 2;}
        else System.out.println("Failed s22: \n" + s22);

        String s33 = menu3.toString();
        if(s33.equals("Spring Menu\n" +
                      "Chicken Alfredo w/ Broccoli (mvdng), MAIN: $19.99\n" +
                      "Vegetable and Nut Pilaf (nvdg), MAIN: $14.99\n" +
                      "Cashew Chicken and Rice (dngmv), MAIN: $17.99\n" +
                      "Pad Thai (nvdmg), MAIN: $17.99\n" +
                      "Beef Stroganoff (mvd), MAIN: $16.99\n" +
                      "Clam Chowder (mvd), SOUP: $14.99\n" +
                      "Mamas Chicken and Noodle Soup (mvg), SOUP: $8.99\n" +
                      "Beef Stew (mvd), SOUP: $8.99\n" +
                      "Vegetable Stew (vgd), SOUP: $7.99\n" +
                      "Chicken Caesar Salad (dngmv), SIDE: $9.99\n" +
                      "Winter Salad (vgn), SIDE: $7.99\n" +
                      "Southwest Salad (nvdmg), SIDE: $8.99\n" +
                      "Carrot Cake (vgd), DESSERT: $9.99\n" +
                      "Rice Pudding with Almonds (nvdg), DESSERT: $7.99\n" +
                      "Pumpkin Pie with Pecans (nvdg), DESSERT: $8.99\n")){ tScore += 2;}
        else System.out.println("Failed s33: \n" + s33);
        System.out.println("  - toString: " + tScore + "/6");
        menuScore += tScore;
        tScore = 0;


        /** Extra Credit */
        // removeDish tests
        if(menu3.removeDish(d12) && menu3.getMenuSize() == 14
           && menu3.toString().equals("Spring Menu\n" +
                                      "Chicken Alfredo w/ Broccoli (mvdng), MAIN: $19.99\n" +
                                      "Vegetable and Nut Pilaf (nvdg), MAIN: $14.99\n" +
                                      "Cashew Chicken and Rice (dngmv), MAIN: $17.99\n" +
                                      "Pad Thai (nvdmg), MAIN: $17.99\n" +
                                      "Beef Stroganoff (mvd), MAIN: $16.99\n" +
                                      "Clam Chowder (mvd), SOUP: $14.99\n" +
                                      "Mamas Chicken and Noodle Soup (mvg), SOUP: $8.99\n" +
                                      "Beef Stew (mvd), SOUP: $8.99\n" +
                                      "Vegetable Stew (vgd), SOUP: $7.99\n" +
                                      "Chicken Caesar Salad (dngmv), SIDE: $9.99\n" +
                                      "Winter Salad (vgn), SIDE: $7.99\n" +
                                      "Carrot Cake (vgd), DESSERT: $9.99\n" +
                                      "Rice Pudding with Almonds (nvdg), DESSERT: $7.99\n" +
                                      "Pumpkin Pie with Pecans (nvdg), DESSERT: $8.99\n")
           && Arrays.equals(menu3.getDishes(), menu3DishesAfterRemove)) { tScore += 5;}
        else System.out.println("Failed removal!");
        System.out.println("  - *Bonus* removeDish: " + tScore + "/5");
        menuScore += tScore;
        tScore = 0;

        System.out.println("Menu implementation score: " + menuScore + "/35");
    }

    public static void main(String[] args) {
        dishTester();
        System.out.println();
        menuTester();
    }
}
