/**
 * Created with IntelliJ IDEA.
 * User: Sholtun
 * Date: 16.09.13
 * Time: 23:15
 * To change this template use File | Settings | File Templates.
 */
public class Function {
    private static final double step = 0.0001;
    public double getValue(double argument){
        return Math.abs(2*Math.pow(argument, 3) - argument * argument + argument + 1);
    }

    public double max(double leftLimit, double rightLimit){
        double max = getValue(leftLimit);
        for (double x = leftLimit; x < rightLimit; x += step){
            double temp = getValue(x);
            if (temp > max) max = temp;
        }
        return max;
    }
}
