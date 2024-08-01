import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(String title) {
        super(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new Dimension(400, 400));
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

}
