import java.util.List;

public class Perceptron {

    Layer[] layers;
    int[] neuronsPerLayer = {2,4,6,6,6,3,1};
    float learningRatio;
    float allowedError;
    float realError = 9999;
    int maxIterations = 1000000;
    List<float[][]> deltas;
    List<float[]> sigmas;

    Perceptron(int numOfLayers)
    {
        learningRatio = 0.2f;
        allowedError = 0.01f;
        layers = new Layer[numOfLayers];

        for (int i = 0; i < numOfLayers; i++) {

            int prevNeurons = i == 0 ? neuronsPerLayer[0] : neuronsPerLayer[i-1];
            layers[i] = new Layer(neuronsPerLayer[i], prevNeurons);

            prevNeurons = i == 0 ? 1 : neuronsPerLayer[i-1];
            float[][] d = new float[neuronsPerLayer[i]][prevNeurons];
            deltas.add(d);
            sigmas.add(new float[neuronsPerLayer[i]]);
        }

        Learn(allowedError, maxIterations);
    }

    void Learn(float allowedE, int iterations)
    {
        Examples e = new Examples();

        for (int j = 0; (j < iterations) && (realError > allowedE); j++) {
            Activate(e);
            realError = CalculateError(e);
            ModifyWeights(e);
        }
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

    void ModifyWeights(Examples e)
    {

    }

    float CalculateError(Examples e)
    {
        return Math.abs(layers[layers.length-1].outputs[0] - e.output[0]);
    }
}
