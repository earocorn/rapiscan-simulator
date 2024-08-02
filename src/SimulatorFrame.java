import panels.SimulatorPanel;

import javax.swing.*;
import java.awt.*;

public class SimulatorFrame extends JFrame {

    JPanel mainPanel;
    SimulatorPanel simulatorPanel;
    CardLayout cardLayout;
    public SimulatorFrame() {
        setSize(900,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Daily File Replay");

        mainPanel = new JPanel();
        simulatorPanel = new SimulatorPanel();
        cardLayout = new CardLayout();

        mainPanel.setLayout(cardLayout);
        mainPanel.add(simulatorPanel);

        add(mainPanel);

        pack();
        setVisible(true);
    }

    public SimulatorPanel getSimulatorPanel() {
        return simulatorPanel;
    }

}
