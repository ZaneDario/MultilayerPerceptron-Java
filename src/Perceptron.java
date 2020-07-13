public class Perceptron {

    Layer[] layers;
    int[] neuronsPerLayer = {2,4,6,6,6,3,1};
    float learningRatio;
    float allowedError;

    Perceptron(int numOfLayers)
    {
        learningRatio = 0.2f;
        allowedError = 0.01f;
        layers = new Layer[numOfLayers];

        for (int i = 0; i < numOfLayers; i++) {
            layers[i] = new Layer(neuronsPerLayer[i]);
        }
    }
}
