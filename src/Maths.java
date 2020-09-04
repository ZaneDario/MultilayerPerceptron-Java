public class Maths {
    public static float Sigmoid(float n)
    {
        return (float)(1/(1+Math.pow(Math.E,-n)));
    }

    public static float InverseSigmoid(float n)
    {
        return (1 - Sigmoid(n)) * Sigmoid(n);
    }

    public static float ArraySum(float[] array)
    {
        float sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += Math.abs(array[i]);
        }
        return sum;
    }

    public static int limit = 1; //To get results from zero to numbers bigger than one.
}
