/**
 * Class for playing with Lights Out puzzles.
 * @author Alexander Leon
 */
public class LightsOut {

    /** Default number of rows in a puzzle. */
    public static final int DEFAULT_ROWS = 5;

    /** Default number of columns in a puzzle. */
    public static final int DEFAULT_COLS = 5;

    /** Character for light on in string representation. */
    public static final char LIGHT_ON = 'X';

    /** Character for light off in string representation. */
    public static final char LIGHT_OFF = '.';

    /**
     * Create a lights out puzzle grid of given dimensions with all lights off.
     * @param rows Number of rows
     * @param cols Number of columns
     * @return 2D array of booleans with all values set to false.
     */
    public static boolean[][] makeEmptyGrid(int rows, int cols) {
        boolean [][] emptyGrid = new boolean [rows][cols];
        for (int i=0; i<rows;i++){
            for (int j=0; j<cols;j++){
                emptyGrid[i][j] = false;
            }
        }
        return emptyGrid;
    }

    /**
     * Given a 2D array of booleans, how many rows does it have?
     * @param arr The array in question
     * @return The number of rows in the array.
     */
    public static int numRows(boolean[][] arr) {
        return arr.length;
    }

    /**
     * Given a 2D array of booleans, how many columns does it have?
     * @param arr The array in question
     * @return The number of columns in the array.
     */
    public static int numCols(boolean[][] arr) {
        if (arr.length>0) {
            return arr[0].length;
        } else {
            return 0;
        }
    }

    /**
     * Creates a string representation of the game board.
     * Rows end with a newline character.
     * @param grid The grid of lights
     * @return String representation of the board
     */
    public static String gridToString(boolean[][] grid) {
        String result = "";
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                result += grid[i][j] ? LIGHT_ON : LIGHT_OFF;
            }
            result += "\n";
        }
        return result;
    }

    /**
     * Toggle the status of a single light, if possible.
     * @param grid The game board
     * @param row The row of the light location
     * @param col The column of the light location
     * @return Turns light at location on if it was off and off it was on and
     *         returns true. Returns false without changing the board if the
     *         location was invalid.
     */
    public static boolean toggleSingleLight(boolean[][] grid, int row,
                                            int col) {
        if (row >= 0 && row < grid.length && col >= 0 &&
                col < grid[0].length) {
            grid[row][col] = !grid[row][col];
            return true;
        }
        return false;
    }

    /**
     * Toggle the status of a light and its neighbors, if possible, and
     * return how many lights were toggled.
     * @param grid The game board
     * @param row The row of the light location
     * @param col The column of the light location
     * @return Toggles on/off status of light and its vertical and horizontal
     *         neighbors and returns number of lights toggled. Returns 0 without
     *         changing the board if the location was invalid.
     */
    public static int toggleLightWithNeighbors(boolean[][] grid, int row,
                                               int col) {
        int numRows = numRows(grid);
        int numCols = numCols(grid);
        int lightsToggled = 0;

        if (toggleSingleLight(grid,row,col)) {
            lightsToggled++;
            // Toggle neighbors: up, down, left, right
            if (row > 0) {
                grid[row - 1][col] = !grid[row - 1][col];
                lightsToggled++;
            }
            if (row < numRows - 1) {
                grid[row + 1][col] = !grid[row + 1][col];
                lightsToggled++;
            }
            if (col > 0) {
                grid[row][col - 1] = !grid[row][col - 1];
                lightsToggled++;
            }
            if (col < numCols - 1) {
                grid[row][col + 1] = !grid[row][col + 1];
                lightsToggled++;
            }
        }
        return lightsToggled;
    }

    /**
     * Selects a random location on the board and toggles it with its neighbors.
     * @param grid The game board.
     */
    public static void toggleRandomLocation(boolean[][] grid) {
        int numRows = numRows(grid);
        int numCols = numCols(grid);

        // Generate random row and column indices
        int randomRow = (int) (Math.random() * numRows);
        int randomCol = (int) (Math.random() * numCols);

        // Toggle the light at the random location and its neighbors
        toggleLightWithNeighbors(grid, randomRow, randomCol);
    }

    /**
     * Create a random solvable puzzle of desired size.
     *
     * To make a solvable puzzle, turn on lights by toggling a random location
     * with its neighbors. (The solution will be to toggle those same
     * positions.)
     * Toggle roughly half the locations. (It's okay if you toggle the same
     * place
     * more than once, undoing your earlier random toggle.)
     *
     * @param rows Number of rows
     * @param cols Number of columns
     * @return Game board with some lights turned on.
     */
    public static boolean[][] makeRandomPuzzle(int rows, int cols) {
        boolean[][] grid = makeEmptyGrid(rows, cols);
        int halfLocations = (rows * cols) / 2;
        for (int i = 0; i < halfLocations; i++) {
            toggleRandomLocation(grid);
        }
        return grid;
    }

    /**
     * Is this puzzle solved?
     * In other words, are all the lights off?
     * @param grid The puzzle grid
     * @return True if all lights are off, false if any are on.
     */
    public static boolean isSolved(boolean[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Creates puzzle array and starts game GUI. */
    public static void main(String[] args) {
        boolean[][] b = makeRandomPuzzle(DEFAULT_ROWS, DEFAULT_COLS);
        LightsOutGUI.showGUI(b);
    }
}
