public class RobotMoving {
    // Static constant for direction
    static final String RIGHT = "→";
    static final String DOWN = "↓";
    static final String DIAGONAL = "↘";
    static final String ORIGIN = "·";

    void bottomUpCost(int n, double[] cost){

        // Declaring variable that will be used to better readability
        double right;
        double down;
        double diagonal;

        // 2D array of doubles for mapping the costs
        double[][] matrixCost = new double[n][n];

        // 2D array of String for mapping the movements to reach the cost above
        String [][] matrixMovements = new String[n][n];

        // Initializing an array of doubles or int fills it with zeros, but null for an array of String
        // Setting the origin for better readability of the matrix of movements
        matrixMovements[0][0] = ORIGIN;

        // 1st for loop - Setting up the cost of the top of the map
        for (int i = 1; i < n; i++){
            // Calculating the cost dynamically by adding it every time to the previous cost [0][i-1]
            matrixCost[0][i] = matrixCost[0][i-1] + cost[0];
            // Adding the adequate direction to the matrix of movements
            matrixMovements[0][i] = RIGHT;
        }

        // 2nd for loop - Setting up the cost of the left most column (side of the matrix going down from the origin)
        for (int i = 1; i < n; i++) {
            // Same as the 1st loop but this time with the cost of going down
            matrixCost[i][0] = matrixCost[i-1][0] + cost[1];
            // Adding the adequate direction to the matrix of movements
            matrixMovements[i][0] = DOWN;

            // 3rd for loop - filling the remaining cost (so excluding the top and left-side)
            for (int j = 1; j < n ; j++) {

                /* Assigning the variable defined previously. Predefining the cost to a named variable to understand
                the comparison of value easier than just using T[i][j].
                right: - cost of going right + cost of the previous on the left
                down: - cost of going down + cost of the previous on the top
                diagonal: - cost of going in diagonal + cost of the previous in diagonal
                */

                right = matrixCost[i][j-1] + cost[0];
                down = matrixCost[i-1][j] + cost[1];
                diagonal = matrixCost[i-1][j-1] + cost[2];

                /* Very simple method to fill the matrix with the lowest value of the three above.
                 Only issue, it doesn't let us fill our matrixMovements without another set of if statements
                 matrixCost[i][j] = Math.min(diagonal, Math.min(right, down)) */

                /* Checking which value is the lowest
                Populating @matrixCost with the best cost, and @matrixMovements with the adequate movement
                - 1st Checking if right is the smallest value
                - Then, checking if down or diagonal is smaller */
                if (right < down && right < diagonal){
                    matrixCost[i][j] = right;
                    matrixMovements[i][j] = RIGHT;
                } else if (down < diagonal) {
                    matrixCost[i][j] = down;
                    matrixMovements[i][j] = DOWN;
                } else {
                    matrixCost[i][j] = diagonal;
                    matrixMovements[i][j] = DIAGONAL;
                }
            }
        }

        // Display information previously found
        printInfo(matrixCost, matrixMovements, n);

        // Display the best movement recursively
        printBestMovement(matrixCost, matrixMovements, n-1, n-1);

    }

    void printInfo(double[][] matrixCost, String [][] matrixMovements, int n){
        // Printing the matrix of cost
        System.out.println("Matrix of cost: ");
        for (int i = 0; i < n; i++){
            System.out.print("|");
            for (int j=0; j<n; j++)
                System.out.printf(("%4.1f|"), matrixCost[i][j]);
            System.out.println();
        }

        // Printing the matrix of movements
        System.out.println("Matrix of movements: ");
        for (int i = 0; i < n; i++){
            System.out.print("|");
            for (int j=0; j<n; j++)
                System.out.printf((" %2s |"), matrixMovements[i][j]);
            System.out.println();
        }

        // Printing the best possible price using the last value of the matrix n,n
        System.out.printf("Best possible cost => %.1f\n", matrixCost[n-1][n-1]);
    }

    void printBestMovement(double[][] matrixCost, String [][] matrixMovements, int x, int y){
        /* Recursive method to print a direction to the last node with the best cost (there's multiple way to achieve the best direction)
           Here I start from the last movement and check it's direction, then I go to the opposite direction recursively */
        switch (matrixMovements[x][y]) {
            // Base case - We reached the ORIGIN, print it and stop recursion
            case ORIGIN -> System.out.printf(ORIGIN + " (%.1f) | ", matrixCost[x][y]);
            // If it points RIGHT, I'll recursively go left T[x][y-1]
            case RIGHT -> {
                printBestMovement(matrixCost, matrixMovements, x, y - 1);
                System.out.printf(RIGHT + " (%.1f) | ", matrixCost[x][y]);
            }
            // If it points DOWN, I'll recursively go up T[x-1][y]
            case DOWN -> {
                printBestMovement(matrixCost, matrixMovements, x - 1, y);
                System.out.printf(DOWN + " (%.1f) | ", matrixCost[x][y]);
            }
            // If it points in DIAGONAL, I'll recursively go to the opposite diagonal T[-1][y-1]
            case DIAGONAL -> {
                printBestMovement(matrixCost, matrixMovements, x - 1, y - 1);
                System.out.printf(DIAGONAL + " (%.1f) | ", matrixCost[x][y]);
            }
        }
    }

    void test(){
        /* Predefined cost in the assignment
            @cost[0] - Right
            cost[1] - Down
            cost[2] - Diagonal (downward right)
        **/
        double[] cost1 = {1.1, 1.3, 2.5};
        double[] cost2 = {1.5, 1.2, 2.3};

        // Example Cost1
        System.out.println("Cost 1:\n-------");
        bottomUpCost(5, cost1);

        // Example Cost2
        System.out.println("\n\nCost 2:\n-------");
        bottomUpCost(5, cost2);
    }

}
