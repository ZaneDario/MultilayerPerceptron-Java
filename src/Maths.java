public class Maths {
    public static float Sigmoid(float n)
    {
        return (float)(1/(1+Math.pow(Math.E,-n)));
    }
}
