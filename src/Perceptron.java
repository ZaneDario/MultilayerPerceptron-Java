import java.util.List;

public class Perceptron {

    Layer[] layers;
    int[] neuronsPerLayer = {2,4,6,6,6,3,1};
    float learningRatio;
    float allowedError;
    float realError = 9999;
    float prevError = 0;
    int maxIterations = 10000000;
    List<float[][]> deltas;
    List<float[]> sigmas;

    Perceptron(int numOfLayers)
    {
        learningRatio = 0.25f;
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

        for (int j = 0; j < iterations ; j++) {
            if((realError > allowedE) || (realError < -allowedE))
            {
                Activate(e);
                ModifyWeights(learningRatio, CalculateError(e));
            }
            else
            {
                System.out.println("Error adjusted.");
                break;
            }
        }

        System.out.println("Finished.");
    }

    void Activate(Examples e)
    {
        for (int i = 0; i < layers.length; i++) {
            if(i == 0)
            {
                layers[i].Activate(e.input[0]);
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
                    layer.neurons[j].weights[k] -= lRate * err;
                }
            }
        }
    }

    float CalculateError(Examples e)
    {
        float n = layers[layers.length-1].outputs[0];
        realError = n - e.output[0];

        if(prevError == realError)
        {
            learningRatio += 0.015f;
            System.out.println("Modifying LR.");
        }


        prevError = realError;
        System.out.println(realError);
        return realError * Maths.InverseSigmoid(n);
    }
}
