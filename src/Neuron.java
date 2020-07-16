import java.util.Random;

public class Neuron {

    Random r = new Random();
    float[] weights;
    float[] inputs;
    float output;
    float bias;

    Neuron(int nOfInputs)
    {
        bias = r.nextFloat() * 2 - 1;
        inputs = new float[nOfInputs];

        for (int i = 0; i < inputs.length; i++) {
            weights[i] = r.nextFloat() * 10 - 5;
        }
    }

    float Activate(float[] input)
    {
        float out = bias;
        for (int i = 0; i < input.length; i++) {
            out += weights[i] * input[i];
        }
        output = Maths.Sigmoid(out);
        return output;
    }
}
