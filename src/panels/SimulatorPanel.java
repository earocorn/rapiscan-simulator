package panels;

import components.InputComponent;
import components.MessageComponent;

import javax.swing.*;
import java.awt.*;

public class SimulatorPanel extends JPanel {

    InputComponent inputComponent;
    MessageComponent messageComponent;

    public SimulatorPanel(){
        setSize(900,600);
        inputComponent = new InputComponent();
        messageComponent = new MessageComponent();
        setLayout(new GridLayout(2, 1));
        setBackground(Color.LIGHT_GRAY);
        add(messageComponent);
        add(inputComponent);
    }

    public InputComponent getInputComponent() {
        return inputComponent;
    }
    public MessageComponent getMessageComponent() {
        return messageComponent;
    }
}
