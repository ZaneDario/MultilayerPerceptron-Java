import java.util.Random;

public class Neuron {

    Random r;
    float[] weights;
    float[] inputs;
    float output;
    float bias;

    Neuron(int nOfInputs)
    {
        r = new Random();
        bias = r.nextFloat() * 2 - 1;
        inputs = new float[nOfInputs];
        weights = new float[nOfInputs];

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
        out = out > 10 ? 10 : out < -10 ? -10 : out;
        output = Maths.Sigmoid(out);
        //System.out.println(out + " --> " + output);
        return output;
    }
}
