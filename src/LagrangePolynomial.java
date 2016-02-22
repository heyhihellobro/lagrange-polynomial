
/**
 * Created by Vladimir Rodin.
 * Built on: Thinkpad Workstation W540
 * Date: 02.02.2016
 * Twitter: @heyhihellobro
 */
public class LagrangePolynomial {

    /**
     * Входные параметры
     */
    static double a = -Math.PI / 3;
    static double b = Math.PI / 3;
    static double h = (b - a) / 2;
    static double n = 2;
    static double[] roots;
    static double[] functionValuesInRoots;

    /**
     * Заданная функция по заданию
     *
     * @param x
     * @return Math.sin(x) + Math.cos(x)
     */
    private static double f(double x) {
        return Math.sin(x) + Math.cos(x);
    }

    /**
     * Первая производная заданной функции
     *
     * @param x
     * @return Math.cos(x) - Math.sin(x);
     */
    private static double ff(double x) {
        return Math.cos(x) - Math.sin(x);
    }

    /**
     * Нахождение x0, x1, x2, ... , xn
     * Нахождение f(x0), f(x1), f(x2), ... , f(xn)
     *
     * @param n
     */
    private static void findRootsAndFunctionValues(double n) {

        System.out.println("============== Процесс нахождения корней и значений функции в корнях ==============");
        roots = new double[(int) n + 1];
        functionValuesInRoots = new double[(int) n + 1];

        for (int i = 0; i <= n; i++) {
            roots[i] = a + i * h;
            functionValuesInRoots[i] = f(roots[i]);
            System.out.println("Значение x[" + i + "] = " + roots[i]);
            System.out.println("Значение функции в f[" + roots[i] + "] = " + functionValuesInRoots[i]);
        }

    }

    private static double solveLagrange(double x, double[] roots, double[] functionValuesInRoots) {
        System.out.println();
        System.out.println("============== Нахождение значения Интерполяционного многочлена Лагранжа ==============");

        double result = 0;
        for (int i = 0; i < roots.length; i++) {
            double temp = 1; //значение l0, l1, l2, l3
            for (int j = 0; j < functionValuesInRoots.length; j++) {
                if (j != i) {
                    temp *= (x - roots[j]) / (roots[i] - roots[j]);
                }
            }
            result += temp * functionValuesInRoots[i];
        }
        double temp = f(x) - result;
        System.out.println("f(x) - Ln(x) = " + temp);
        return result;
    }

    private static double solveNeuton(double x, double[] roots, double[] functionValuesInRoots) {

        System.out.println();
        System.out.println("============== Нахождение значения Интерполяционного многочлена Ньютона ==============");

        double result = functionValuesInRoots[0], F, den;
        int i, j, k;
        for (i = 1; i <= n; i++) {
            F = 0;
            for (j = 0; j <= i; j++) {
                den = 1;
                for (k = 0; k <= i; k++) {
                    if (k != j) den *= (roots[j] - roots[k]);
                }
                F += functionValuesInRoots[j] / den;
            }
            for (k = 0; k < i; k++) F *= (x - roots[k]);
            result += F;
        }

        double temp = f(x) - result;
        System.out.println("f(x) - Pn(x) = " + temp);
        return result;
    }


    public static void main(String[] args) {

        findRootsAndFunctionValues(n);
        System.out.println(solveLagrange(0.5, roots, functionValuesInRoots));
        System.out.println();
        System.out.println(solveNeuton(0.5, roots, functionValuesInRoots));

    }
}
