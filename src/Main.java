import models.SimulatorState;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.BindException;

public class Main {
    static Simulator simulator;

    public static void main(String[] args) {

        SimulatorFrame simulatorFrame = new SimulatorFrame();

        var panel = simulatorFrame.getSimulatorPanel();
        var input = panel.getInputComponent();
        var message = panel.getMessageComponent();

        input.getStartButton().addActionListener((event) -> {
            if(!input.getFileNameInput().getText().isEmpty()
            && !input.getPortInput().getText().isEmpty()) {
                try {
                    simulator = new Simulator(
                            Integer.parseInt(input.getPortInput().getText()),
                            input.getFileNameInput().getText(),
                            input.getQuickReadBox().isSelected());
                    simulator.init();

                    simulator.addScanListener(scan -> {
                        input.getCSVLineField().setText(scan);
                    });
                    simulator.addStateListener(state -> {
                        if(state == SimulatorState.WAITING) {
                            message.setMessage("Server started. Awaiting client connection...", new Color(141, 173, 0));
                        } else if(state == SimulatorState.CONNECTED) {
                            message.setMessage("Connected to client! Listen to port " + input.getPortInput().getText() + " for RPM scans.", new Color(0, 95, 11));
                        } else if(state == SimulatorState.ERROR) {
                            message.setMessage("File not found!", new Color(180, 30, 0));
                        } else if(state == SimulatorState.ENDED) {
                            message.setMessage("Connection closed! No more data.", new Color(180, 30, 0));
                        }
                    });

                    simulator.start();
                } catch (IOException e) {
                    if (e instanceof BindException)
                        message.setMessage(e.getLocalizedMessage() + " : " + input.getPortInput().getText(), Color.RED);
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