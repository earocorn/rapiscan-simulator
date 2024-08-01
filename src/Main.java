import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Simulator simulator = new Simulator(9090, "rpm_data1.txt");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}