public class Main {
    public static void main(String[] args) {
        double x[] = {-1.48, -1.3756, -1.2834, -1.228, -1.17, 0.24022, 0.120902,0.1905, 2024.1501};

        for (double v : x) {
            System.out.printf("%f,%f\n", v, Math.cos(v));
        }

        System.out.println("Hello world!");
    }
}