import java.awt.*;

/**
 * Sand simulation project adapted from http://nifty.stanford.edu/2017/feinberg-falling-sand/
 * Student name: @Alexander Leon
 *
 * Metal:
 * Metal particles do not move or interact with other elements
 * They act as solid barriers, staying stationary in their initial placed
 * position
 *
 * Water:
 * Water particles move randomly to empty spaces left, right or down
 * They can swap places with sand particles, allowing sand to fall through water
 *
 * Sand:
 * Sand particles fall down into empty spaces below them
 * If on a solid surface, they may slide randomly left or right into empty spaces
 * Sand can fall through water by swapping places with water particles
 *
 * Acid:
 * Acid particles are destructive - they turn particles below them into empty space
 * They flow down if possible, otherwise randomly left or right, like water
 * Acid falls through and destroys other elements like sand and water
 *
 * The main interactions are: *
 * -Sand can fall and slide through water
 * -Acid destroys other particles below it
 * -Acid causes sand to fall down when it turns spaces below into empty
 *
 */
public class SandSimulator {

    /**
     * Enum for material types of the particles
     */
    public enum Material {
        EMPTY,
        METAL,
        SAND,
        WATER,
        OIL,
        ACID,
        MAGMA
    }

    private static final Color METAL_COLOR = Color.GRAY;
    private static final Color SAND_COLOR = Color.YELLOW;
    private static final Color WATER_COLOR = Color.BLUE;
    private static final Color OIL_COLOR = Color.ORANGE;
    private static final Color MAGMA_COLOR = Color.RED;
    private static final Color MY_FANCY_COLOR = new Color(0, 252, 16);

    /** grid of particles of various materials*/
    private Material[][] grid;

    /** The display window */
    private SandDisplay display;

    /**
     * Create a new SandSimulator of given size.
     * @param numRows number of rows
     * @param numCols number of columns
     */
    public SandSimulator(int numRows, int numCols) {

        String[] names = new String[]{"Empty", "Metal", "Water", "Sand",
                "Acid", "Oil"};
        display = new SandDisplay("Falling Sand", names, numRows, numCols);

        grid = new Material[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                grid[i][j] = Material.EMPTY;
            }
        }
    }

    /**
     * Called after the user clicks on a location using the given tool
     * @param row Row of location
     * @param col Column of location
     * @param tool Name of selected tool
     */
    public void updateFromUser(int row, int col, String tool) {
        Material selectedMaterial = null;

        switch (tool) {
            case "Empty":
                selectedMaterial = Material.EMPTY;
                break;
            case "Metal":
                selectedMaterial = Material.METAL;
                break;
            case "Water":
                selectedMaterial = Material.WATER;
                break;
            case "Sand":
                selectedMaterial = Material.SAND;
                break;
            case "Oil":
                selectedMaterial = Material.OIL;
                break;
            case "Acid":
                selectedMaterial = Material.ACID;
                break;
            case "Magma":
                selectedMaterial = Material.MAGMA;
                break;
        }

        if (selectedMaterial != null) {
            grid[row][col] = selectedMaterial;
        }
    }

    /**
     * copies each element of grid into the display
     */
    public void refreshDisplay() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                switch (grid[i][j]) {
                    case EMPTY:
                        display.setColor(i, j, Color.BLACK);
                        break;
                    case METAL:
                        display.setColor(i, j, METAL_COLOR);
                        break;
                    case SAND:
                        display.setColor(i, j, SAND_COLOR);
                        break;
                    case WATER:
                        display.setColor(i, j, WATER_COLOR);
                        break;
                    case OIL:
                        display.setColor(i, j, OIL_COLOR);
                        break;
                    case ACID:
                        display.setColor(i, j, MY_FANCY_COLOR);
                        break;
                    case MAGMA:
                        display.setColor(i, j, MAGMA_COLOR);
                        break;
                }
            }
        }
    }

    /**
     * Update the simulation by one step.
     * Called repeatedly.
     * Causes one random particle to maybe do something
     */
    public void updateRandomLocation() {
        int r = (int)(Math.random() * grid.length);
        int c = (int)(Math.random() * grid[0].length);

        Material current = grid[r][c];

        if(current == Material.SAND) {
            if (r < grid.length - 1 &&
                    (grid[r+1][c] == Material.EMPTY ||
                            grid[r+1][c] == Material.WATER)) {

                Material temp = grid[r][c];
                grid[r][c] = grid[r+1][c];
                grid[r+1][c] = temp;

            } else if (r > 0 && grid[r-1][c] != Material.EMPTY &&
                    c < grid[0].length - 1 &&
                    (grid[r][c+1] == Material.EMPTY ||
                            grid[r][c+1] == Material.WATER)) {

                Material temp = grid[r][c];
                grid[r][c] = grid[r][c+1];
                grid[r][c+1] = temp;

            }

        } else if(current == Material.WATER) {

            int dir = (int)(Math.random() * 3);

            if (dir == 0 && r < grid.length - 1 &&
                    (grid[r+1][c] == Material.EMPTY ||
                            grid[r+1][c] == Material.SAND)) {

                Material temp = grid[r][c];
                grid[r][c] = grid[r+1][c];
                grid[r+1][c] = temp;

            } else if (dir == 1 && c > 0 &&
                    (grid[r][c-1] == Material.EMPTY ||
                            grid[r][c-1] == Material.SAND)) {

                Material temp = grid[r][c];
                grid[r][c] = grid[r][c-1];
                grid[r][c-1] = temp;

            } else if (dir == 2 && c < grid[0].length-1 &&
                    (grid[r][c+1] == Material.EMPTY ||
                            grid[r][c+1] == Material.SAND)) {

                Material temp = grid[r][c];
                grid[r][c] = grid[r][c+1];
                grid[r][c+1] = temp;

            }

        } else if(current == Material.ACID) {

            if(r < grid.length - 1) {
                grid[r+1][c] = Material.EMPTY;
                Material temp = grid[r][c];
                grid[r][c] = grid[r+1][c];
                grid[r+1][c] = temp;
            } else {

                int dir = (int)(Math.random() * 2);

                if(dir == 0 && c < grid[0].length-1) {

                    grid[r][c+1] = Material.EMPTY;
                    Material temp = grid[r][c];
                    grid[r][c] = grid[r][c+1];
                    grid[r][c+1] = temp;

                } else if(dir == 1 && c > 0) {

                    grid[r][c-1] = Material.EMPTY;
                    Material temp = grid[r][c];
                    grid[r][c] = grid[r][c-1];
                    grid[r][c-1] = temp;

                }
            }
        } else if(current == Material.OIL) {

            int dir = (int)(Math.random() * 3);

            if (dir == 0 &&
                    r < grid.length - 1 &&
                    (grid[r+1][c] == Material.EMPTY ||
                            grid[r+1][c] == Material.SAND)) {

            } else if (dir == 1 &&
                    c > 0 &&
                    (grid[r][c-1] == Material.EMPTY ||
                            grid[r][c-1] == Material.SAND)) {


            } else if (dir == 2 &&
                    c < grid[0].length-1 &&
                    (grid[r][c+1] == Material.EMPTY ||
                            grid[r][c+1] == Material.SAND)) {


            }

        }

    }

    /**
     * Run the SandSimulator particle simulation.
     *
     * DO NOT MODIFY THIS METHOD!
     */
    public void run() {
        // keep updating as long as the program is running
        while (true) {
            // update some number of particles, as determined by the speed
            // slider
            for (int i = 0; i < display.getSpeed(); i++) {
                updateRandomLocation();
            }
            // Update the display object's colors
            refreshDisplay();
            // wait for redrawing and for mouse events
            display.repaintAndPause(1);

            int[] mouseLoc = display.getMouseLocation();
            //test if mouse clicked
            if (mouseLoc != null) {
                updateFromUser(mouseLoc[0], mouseLoc[1],
                        display.getToolString());
            }
        }
    }

    /** Creates a new SandSimulator and sets it running */
    public static void main(String[] args) {
        SandSimulator sim = new SandSimulator(120, 80);
        sim.run();
    }
}
