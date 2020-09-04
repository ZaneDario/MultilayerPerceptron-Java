import java.util.Scanner;

public class ScannerInterface {
    public static float[] Test()
    {
        Scanner s = new Scanner(System.in);
        System.out.println("First parameter: ");
        int x = s.nextInt();
        System.out.println("Second parameter: ");
        int y = s.nextInt();

        return new float[]{x, y};
    }
}
