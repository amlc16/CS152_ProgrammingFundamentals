/**
 * Class to test the LightsOut methods.
 * If your code does not pass these tests, it is broken.
 *
 * It is possible that your code is broken even if it passes these
 * tests, so make sure the game plays properly, too.
 */
public class LightsOutTester {
    private static int correctTests = 0;
    private static int totalTests = 0;

    /** Clear test count variables */
    private static void clearCounts() {
        correctTests = 0;
        totalTests = 0;
    }

    /**
     * Update test count variables depending on if test passed.
     * @param correct True if test counts as correct.
     */
    private static boolean countTest(boolean correct) {
        if(correct) {
            correctTests++;
        } else {
            // Uncomment next line of code to see which test(s) are failing,
            // but don't do it unless you are only failing a few
            //new Exception().printStackTrace(System.out);
        }
        totalTests++;

        // return the test result so we can use it in branches
        return correct;
    }

    /**
     * Print the testing results.
     * @param name The name of the method(s) tested.
     */
    private static void printResults(String name) {
        String msg = "";
        if(correctTests < totalTests) {
            // I want failed tests to really jump out at you.
            msg = " INCORRECT!";
        }
        System.out.println("testing " + name + ": passes " +
                correctTests + " of " + totalTests + " tests" + msg);
    }

    public static void testGridToString(boolean[][] grid, String expected) {
        String s = LightsOut.gridToString(grid);
        countTest(s != null && s.equals(expected));
    }

    public static void testSize(boolean[][] grid, int rows, int cols) {
        countTest(LightsOut.numRows(grid) == rows);
        countTest(LightsOut.numCols(grid) == cols);
    }

    public static void testStatus(boolean[][] grid, int rows, int cols,
                                  boolean solved, String expectedStr) {
        if(countTest(grid != null)) {
            testSize(grid, rows, cols);
            testGridToString(grid, expectedStr);
            countTest(LightsOut.isSolved(grid) == solved);
        }
    }

    public static void testEmptyGrids() {

        boolean[][] g34 = LightsOut.makeEmptyGrid(3, 4);
        testStatus(g34, 3, 4, true,
                "....\n....\n....\n");

        boolean[][] g52 = LightsOut.makeEmptyGrid(5, 2);
        testStatus(g52, 5, 2, true,
                "..\n..\n..\n..\n..\n");

        boolean[][] g22 = LightsOut.makeEmptyGrid(2, 2);
        testStatus(g22, 2, 2, true,"..\n..\n");

        boolean[][] g55 = LightsOut.makeEmptyGrid(5, 5);
        testStatus(g55, 5, 5, true,
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n");

    }

    public static void testSingleToggle() {
        boolean[][] g34 = LightsOut.makeEmptyGrid(3, 4);
        countTest(!LightsOut.toggleSingleLight(g34, -1, 0));
        countTest(!LightsOut.toggleSingleLight(g34, 0, -3));
        countTest(!LightsOut.toggleSingleLight(g34, 3, 0));
        countTest(!LightsOut.toggleSingleLight(g34, 0, 4));
        testStatus(g34, 3, 4, true,
                "....\n....\n....\n");
        countTest(LightsOut.toggleSingleLight(g34, 0, 3));
        testStatus(g34, 3, 4, false,
                "...X\n....\n....\n");
        countTest(LightsOut.toggleSingleLight(g34, 0, 3));
        testStatus(g34, 3, 4, true,
                "....\n....\n....\n");
        countTest(LightsOut.toggleSingleLight(g34, 2, 1));
        testStatus(g34, 3, 4, false,
                "....\n....\n.X..\n");

        boolean[][] g55 = LightsOut.makeEmptyGrid(5, 5);
        countTest(!LightsOut.toggleSingleLight(g55, -1, 0));
        countTest(!LightsOut.toggleSingleLight(g55, 0, -3));
        countTest(!LightsOut.toggleSingleLight(g55, 5, 0));
        countTest(!LightsOut.toggleSingleLight(g55, 0, 6));
        testStatus(g55, 5, 5, true,
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n");
        countTest(LightsOut.toggleSingleLight(g55, 0, 3));
        testStatus(g55, 5, 5, false,
                   "...X.\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n");
        countTest(LightsOut.toggleSingleLight(g55, 2, 1));
        testStatus(g55, 5, 5, false,
                   "...X.\n" +
                   ".....\n" +
                   ".X...\n" +
                   ".....\n" +
                   ".....\n");

    }

    public static void testToggleWithNeighbors() {
        boolean[][] g34 = LightsOut.makeEmptyGrid(3, 4);
        countTest(LightsOut.toggleLightWithNeighbors(g34, -1, 0) == 0);
        countTest(LightsOut.toggleLightWithNeighbors(g34, 0, -3) == 0);
        countTest(LightsOut.toggleLightWithNeighbors(g34, 3, 0) == 0);
        countTest(LightsOut.toggleLightWithNeighbors(g34, 0, 4) == 0);
        testStatus(g34, 3, 4, true,
                "....\n....\n....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g34, 0, 3) == 3);
        testStatus(g34, 3, 4, false,
                "..XX\n...X\n....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g34, 0, 3) == 3);
        testStatus(g34, 3, 4, true,
                "....\n....\n....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g34, 2, 1) == 4);
        testStatus(g34, 3, 4, false,
                "....\n.X..\nXXX.\n");

        boolean[][] g55 = LightsOut.makeEmptyGrid(5, 5);
        countTest(LightsOut.toggleLightWithNeighbors(g55, -1, 0) == 0);
        countTest(LightsOut.toggleLightWithNeighbors(g55, 0, -3) == 0);
        countTest(LightsOut.toggleLightWithNeighbors(g55, 5, 0) == 0);
        countTest(LightsOut.toggleLightWithNeighbors(g55, 0, 6) == 0);
        testStatus(g55, 5, 5, true,
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g55, 0, 3) == 4);
        testStatus(g55, 5, 5, false,
                   "..XXX\n" +
                   "...X.\n" +
                   ".....\n" +
                   ".....\n" +
                   ".....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g55, 2, 1) == 5);
        testStatus(g55, 5, 5, false,
                   "..XXX\n" +
                   ".X.X.\n" +
                   "XXX..\n" +
                   ".X...\n" +
                   ".....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g55, 1, 2) == 5);
        testStatus(g55, 5, 5, false,
                   "...XX\n" +
                   "..X..\n" +
                   "XX...\n" +
                   ".X...\n" +
                   ".....\n");
        countTest(LightsOut.toggleLightWithNeighbors(g55, 2, 0) == 4);
        testStatus(g55, 5, 5, false,
                   "...XX\n" +
                   "X.X..\n" +
                   ".....\n" +
                   "XX...\n" +
                   ".....\n");

    }

    public static void testRandomLocation() {
        // only one possible result on tiny puzzles
        boolean[][] g1 = LightsOut.makeEmptyGrid(1, 1);
        testStatus(g1, 1, 1, true, ".\n");
        LightsOut.toggleRandomLocation(g1);
        testStatus(g1, 1, 1, false, "X\n");

        boolean[][] g2 = LightsOut.makeEmptyGrid(1, 2);
        testStatus(g2, 1, 2, true, "..\n");
        LightsOut.toggleRandomLocation(g2);
        testStatus(g2, 1, 2, false, "XX\n");

        int[] rowsToTest = {2, 5, 9};
        int[] colsToTest = {3, 5, 8};
        int n = 1000;
        for(int rows : rowsToTest) {
            for (int cols : colsToTest) {
                java.util.Set<String> configs = new java.util.HashSet<>();
                for (int i = 0; i < n; i++) {
                    boolean[][] g = LightsOut.makeEmptyGrid(rows, cols);
                    countTest(LightsOut.isSolved(g));
                    if (countTest(g != null)) {
                        LightsOut.toggleRandomLocation(g);
                        countTest(!LightsOut.isSolved(g));
                        String s = LightsOut.gridToString(g);
                        if (countTest(s != null)) {
                            configs.add(s);
                        }
                    }
                }
                int expectedConfigs = rows * cols;
                countTest(configs.size() == expectedConfigs);
            }
        }
        // TODO: test that random location correctly includes neighbors
    }

    /**
     * Recursively solve puzzle, assuming all lights have been toggled as
     * necessary up to given position.
     * @param grid Partially solved puzzle
     * @param row Row of next position
     * @param col Column of next position
     * @return Number of lights needed to toggle to solve the puzzle,
     *         or -1 if not solvable.
     */
    private static int solveCount(boolean[][] grid, int row, int col) {
        int rows = LightsOut.numRows(grid);
        int cols = LightsOut.numCols(grid);
        if(LightsOut.isSolved(grid)) {
            return 0;
        }
        if(row > 0 && col > 0 && grid[row - 1][col - 1]) return -1;
        if(row > 1 && grid[row - 2][cols - 1]) return -1;
        if(row == rows) return -1;
        if(col == cols) return solveCount(grid, row + 1, 0);

        int toggled = solveCount(grid, row, col + 1);
        if(toggled < 0) {
            LightsOut.toggleLightWithNeighbors(grid, row, col);
            toggled = solveCount(grid, row, col + 1);
            LightsOut.toggleLightWithNeighbors(grid, row, col);
            if(toggled >= 0) {
                toggled++;
            }
        }

        return toggled;
    }

    public static void testRandomPuzzle(int rows, int cols) {
        boolean[][] puzzle = LightsOut.makeRandomPuzzle(rows, cols);
        if(countTest(puzzle != null)) {
            testSize(puzzle, rows, cols);
            int n = solveCount(puzzle, 0, 0);
            countTest(n >= 0);
        }
    }

    public static void testRandomPuzzles() {
        // Make sure an empty puzzle is trivially solved first
        boolean[][] empty = LightsOut.makeEmptyGrid(9, 5);
        if(countTest(empty != null)) {
            int toggles = solveCount(empty, 0, 0);
            countTest(toggles == 0);
        }

        // TODO: verify that expected number of lights were toggled
        // TODO: gather statistical results to make sure puzzles are random?
        int[] rowsToTest = {2, 5, 9};
        int[] colsToTest = {3, 5, 8};
        int n = 1000;
        for(int rows : rowsToTest) {
            for(int cols : colsToTest) {
                for(int i = 0; i < n; i++){
                    testRandomPuzzle(rows, cols);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        // TODO flesh out and split up tests

        clearCounts();
        testEmptyGrids();
        printResults("empty grids");

        clearCounts();
        testSingleToggle();
        printResults("single toggle");

        clearCounts();
        testToggleWithNeighbors();
        printResults("toggle with neighbors");

        clearCounts();
        testRandomLocation();
        printResults("toggle random location");

        clearCounts();
        testRandomPuzzles();
        printResults("random solvable puzzles");
    }
}
