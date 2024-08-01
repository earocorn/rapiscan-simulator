package components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputComponent extends JPanel implements ActionListener {

    private static final String[] labels = {"File Name", "Port", "CSV Line"};
//    JTextField portInput;
//    JTextField fileNameInput;
//    JTextField currentCsvLine;
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

//        portInput = new JTextField();
//        fileNameInput = new JTextField();
//        this.add(fileNameInput);
//        this.add(portInput);

        JPanel buttonContainer = new JPanel();
//        buttonContainer.setLayout(new GridBagLayout());

        startButton = new JButton("Start");
        startButton.addActionListener(e -> System.out.println("Hey"));

        buttonContainer.add(startButton);

        add(inputPanel);
        add(buttonContainer);
//
//        this.add(startButton);
//        this.getPortInput().grabFocus();
//        this.getFileNameInput().grabFocus();

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

    }

    //    public JTextField getPortInput() {
//        return portInput;
//    }
//
//    public void setPortInput(JTextField portInput) {
//        this.portInput = portInput;
//    }
//
//    public JTextField getFileNameInput() {
//        return fileNameInput;
//    }
//
//    public void setFileNameInput(JTextField fileNameInput) {
//        this.fileNameInput = fileNameInput;
//    }
}
