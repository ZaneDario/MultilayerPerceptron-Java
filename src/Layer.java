public class Layer {

    Neuron[] neurons;

    Layer(int nOfNeurons)
    {
        neurons = new Neuron[nOfNeurons];
        for (int i = 0; i < neurons.length; i++) {
            //neurons[i] = new Neuron();
        }
    }
}
