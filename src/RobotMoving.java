public class RobotMoving {

    void bottomUpCost(int n, double[] cost){

        double[][] matrix1 = new double[n][n];
//        int[][] matrix2 = new int[n][n];

        for (int i = 1; i < n; i++) {
            matrix1[0][i] = matrix1[0][i] + cost[0];
        }

        for (int i = 1; i < n; i++) {
            matrix1[i][0] = matrix1[i-1][0] + cost[1];
            for (int j = 1; j < n ; j++) {
                matrix1[i][j] = Math.min(matrix1[i-1][j-1] + cost[2], Math.min(matrix1[i][j-1] + cost[0], matrix1[i-1][j] + cost[1]));
            }
        }
        double finalCost = matrix1[n-1][n-1];

        System.out.println(finalCost);
    }

}
