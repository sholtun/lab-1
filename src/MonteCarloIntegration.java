import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Sholtun
 * Date: 16.09.13
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */
public class MonteCarloIntegration implements Runnable {
    private double leftLimit;
    private double rightLimit;
    private Function function;
    private double result;
    private int iterations;

    public MonteCarloIntegration(double _leftLimit, double _rightLimit, Function _function, int _iterations){
        leftLimit = _leftLimit;
        rightLimit = _rightLimit;
        function = _function;
        iterations = _iterations;
    }
    @Override
    public void run() {
        double min = 0;
        double max = Math.abs(function.max(leftLimit, rightLimit));
        Random rand = new Random();

        int k = 0;
        for (int i = 0; i < iterations; i++)
        {
            double randX = leftLimit + rand.nextDouble() * (rightLimit - leftLimit);
            double randY = min + rand.nextDouble() * (max - min);
            if (randY < function.getValue(randX)){
                k++;
            }
        }
        result = (rightLimit - leftLimit) * (max - min) * ((double)k / (double)iterations);
    }

    public double getResult(){
        return result;
    }
}
