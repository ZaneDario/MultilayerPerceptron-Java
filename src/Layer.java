public class Layer {

    Neuron[] neurons;
    float[] outputs;

    Layer(int nOfNeurons, int prevNeurons)
    {
        neurons = new Neuron[nOfNeurons];
        outputs = new float[nOfNeurons];
        for (int i = 0; i < neurons.length; i++) {
            neurons[i] = new Neuron(prevNeurons);
        }
    }

    void Activate(float[] inputs)
    {
        for (int i = 0; i < neurons.length; i++) {
            outputs[i] = neurons[i].Activate(inputs);
        }
    }
}
