import java.util.Random;

public class Neuron {

    float[] weights;
    float[] inputs;
    float output;
    float bias;

    Neuron(int nOfInputs)
    {
        bias = new Random().nextFloat();
    }
}
