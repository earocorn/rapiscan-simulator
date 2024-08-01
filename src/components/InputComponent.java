package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputComponent extends JPanel implements ActionListener {

    private static final String[] labels = {"File Name", "Port", "CSV Line"};
    JTextField[] fields;
    JButton startButton;
    public InputComponent(){

        JPanel inputPanel = new JPanel();
        fields = new JTextField[labels.length];
        inputPanel.setLayout(new GridLayout(0,2,5,5));

        for(int i=0; i< fields.length; i++){
            fields[i] = new JTextField(10);

            switch(i){
                case 2:
                    fields[i].setEnabled(false);
                    break;
                default: break;
            }
            inputPanel.add(new JLabel(labels[i]));
            inputPanel.add(fields[i]);
        }

        JPanel buttonContainer = new JPanel();

        startButton = new JButton("Start");
        startButton.addActionListener(this);

        buttonContainer.add(startButton);

        add(inputPanel);
        add(buttonContainer);

    }

    public JTextField getFileNameInput(){
        return fields[0];
    }
    public JTextField getPortInput(){
        return fields[1];
    }

    public JButton getStartButton() {
        return startButton;
    }


    public void clearInput(){
       for(int i=0; i< fields.length; i++){
           fields[i].setText("");
       }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == startButton){
            handleStartButton();
        }
    }

    private void handleStartButton(){
        System.out.print("Hey");
    }
    public boolean isNumeric(String text){
        if(text == null || text.trim().equals("")){
            return false;
        }
        for( int i=0; i<text.length(); i++){
            if(!Character.isDigit(text.charAt(i))){
                return false;
            }
        }
        return true;

    }
}
