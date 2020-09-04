import java.util.Scanner;

public class Programme {
    public static void main(String[] args) {

        Perceptron p = new Perceptron(7);

        Scanner s = new Scanner(System.in);
        while(!s.next().equals("exit"))
        {
            p.Check(ScannerInterface.Test());
        }

    }
}
