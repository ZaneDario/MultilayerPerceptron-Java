import java.util.List;

public class Perceptron {

    Layer[] layers;
    int[] neuronsPerLayer = {2,4,6,6,6,3,1};
    float learningRatio;
    float allowedError;
    float[] realError;
    float[] prevError;
    int maxIterations = 100000000;
    List<float[][]> deltas;
    List<float[]> sigmas;

    Perceptron(int numOfLayers)
    {
        learningRatio = 0.012f;
        allowedError = 0.02f;
        layers = new Layer[numOfLayers];

        for (int i = 0; i < numOfLayers; i++) {

            int prevNeurons = i == 0 ? neuronsPerLayer[0] : neuronsPerLayer[i-1];
            layers[i] = new Layer(neuronsPerLayer[i], prevNeurons);

            prevNeurons = i == 0 ? 1 : neuronsPerLayer[i-1];
            float[][] d = new float[neuronsPerLayer[i]][prevNeurons];
            //deltas.add(d);
            //sigmas.add(new float[neuronsPerLayer[i]]);
        }

        Learn(allowedError, maxIterations);
    }

    void Learn(float allowedE, int iterations)
    {
        Examples e = new Examples();

        realError = new float[e.input.length];
        for (int ind = 0; ind < realError.length; ind++) { realError[ind] = 9999; }

        prevError = new float[e.input.length];

        for (int j = 0; j < iterations ; j++) {
            for (int i = 0; i < e.input.length; i++) {
                    Activate(e, i);
                    ModifyWeights(learningRatio, CalculateError(e, i));
            }

            float error = Maths.ArraySum(realError);
            System.out.println(error);
            if(!((error > allowedE) || (error < -allowedE)))
            {
                System.out.println("Error adjusted.");
                break;
            }
        }

        System.out.println("Finished.");
    }

    void Activate(Examples e, int index)
    {
        for (int i = 0; i < layers.length; i++) {
            if(i == 0)
            {
                layers[i].Activate(e.input[index]);
            }
            else
            {
                layers[i].Activate(layers[i-1].outputs);
            }
        }
    }

    void ModifyWeights(float lRate, float err)
    {
        for (Layer layer : layers) {
            for (int j = 0; j < layer.neurons.length; j++) {
                for (int k = 0; k < layer.neurons[j].weights.length; k++) {
                    float r = layer.neurons[j].r.nextFloat();
                    layer.neurons[j].weights[k] -= lRate * err * r;
                }
            }
        }
    }

    float CalculateError(Examples e, int index)
    {
        float n = layers[layers.length-1].outputs[0];
        realError[index] = n - e.output[index];

        if(prevError[index] == realError[index])
        {
            learningRatio += 0.015f;
            System.out.println("Modifying LR.");
        }

        prevError[index] = realError[index];
        System.out.println(index + " ---> " + realError[index]);
        return realError[index] * Maths.InverseSigmoid(n);
    }

    public void Check(float[] inp)
    {
        for (int i = 0; i < layers.length; i++) {
            if(i == 0)
            {
                layers[i].Activate(inp);
            }
            else
            {
                layers[i].Activate(layers[i-1].outputs);

                if(i == layers.length - 1){
                    System.out.println(layers[i].neurons[0].output * Maths.limit);
                }
            }
        }
    }
}
