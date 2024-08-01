import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    static Simulator simulator;

    public static void main(String[] args) {

        SimulatorFrame simulatorFrame = new SimulatorFrame();

        var panel = simulatorFrame.getSimulatorPanel();
        var input = panel.getInputComponent();

        input.getStartButton().addActionListener((event) -> {
            if(!input.getFileNameInput().getText().isEmpty()
            && !input.getPortInput().getText().isEmpty()) {
                try {
                    simulator = new Simulator(
                            Integer.parseInt(input.getPortInput().getText()),
                            input.getFileNameInput().getText());
                    simulator.addListener(message -> {
                        input.getCSVLineField().setText(message);
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        simulatorFrame.addWindowStateListener(e -> {
            if(e.getNewState() == WindowEvent.WINDOW_CLOSED && simulator != null) {
                simulator.stop();
            }
        });
    }
}