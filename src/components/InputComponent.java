package components;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputComponent extends JPanel {

    private static final String[] labels = {"File Name", "Port", "Scan Data"};
    JTextField[] fields;
    JButton startButton;
    JCheckBox quickReadBox;

    public InputComponent(){

        JPanel inputPanel = new JPanel();
        fields = new JTextField[labels.length];
        inputPanel.setLayout(new GridLayout(0,2,5,5));

        for(int i=0; i< fields.length; i++){
            fields[i] = new JTextField(20);

            switch(i){
                case 2:
                    fields[i].setEnabled(false);
                    fields[i].setDisabledTextColor(Color.RED);
                    break;
                default: break;
            }
            inputPanel.add(new JLabel(labels[i]));
            inputPanel.add(fields[i]);
        }

        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new GridLayout(3, 1));

        startButton = new JButton("Start");
        quickReadBox = new JCheckBox("Quick Read");
        JButton openFileButton = new JButton("Choose File");

        openFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.addActionListener(e1 -> {
                    if(fileChooser.getSelectedFile() != null) {
                        getFileNameInput().setText(fileChooser.getSelectedFile().getAbsolutePath());
                    }
                });
            fileChooser.showOpenDialog(null);
        });

        buttonContainer.add(openFileButton);
        buttonContainer.add(quickReadBox);
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
    public JTextField getCSVLineField(){ return fields[2]; }
    public JButton getStartButton() {
        return startButton;
    }

    public JCheckBox getQuickReadBox() {
        return quickReadBox;
    }

    public void clearInput(){
       for(int i=0; i< fields.length; i++){
           fields[i].setText("");
       }
    }
}
