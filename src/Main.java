public class Main {

    public static void main(String[] args) {

        if (args.length != 3) return;

        long time = System.currentTimeMillis();
        int Iterations = 10000000;
        int threadsCount = Integer.parseInt(args[0]);
        double leftLimit = Double.parseDouble(args[1]);
        double rightLimit = Double.parseDouble(args[2]);
        Thread[] threads = new Thread[threadsCount];
        MonteCarloIntegration[] integrations = new MonteCarloIntegration[threadsCount];
        Function func = new Function();

        double intervalSize = (rightLimit - leftLimit)/threadsCount;

        for (int i = 0; i < threadsCount; i++){
            integrations[i] = new MonteCarloIntegration(leftLimit + intervalSize * i, leftLimit + intervalSize * (i + 1), func, Iterations / threadsCount);
            threads[i] = new Thread(integrations[i]);
            threads[i].start();
        }
        try {
            for(Thread thread : threads){
                thread.join();
            }
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return;
        }
        double result = 0;
        for(MonteCarloIntegration integration : integrations){
            result += integration.getResult();
        }
        System.out.println("Result: " + result);
        System.out.println("Time: " + (double)(System.currentTimeMillis() - time) / 1000);
    }
}
