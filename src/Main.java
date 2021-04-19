public class Main {

    public static void main(String[] args) {

        RobotMoving robotMoving = new RobotMoving();

        double[] cost1 = {1.1, 1.3, 2.5};
        double[] cost2 = {1.5, 1.2, 2.3};

        robotMoving.bottomUpCost(10, cost1);


    }
}
