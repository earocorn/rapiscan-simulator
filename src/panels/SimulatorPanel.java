package panels;

import components.InputComponent;

import javax.swing.*;
import java.awt.*;

public class SimulatorPanel extends JPanel {

    InputComponent inputComponent;

    public SimulatorPanel(){
        setSize(900,600);
        inputComponent = new InputComponent();
        setBackground(Color.LIGHT_GRAY);
        add(inputComponent);
    }
}
