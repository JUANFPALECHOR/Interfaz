package myPresentation;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {
    public Title(String title, Color backgroundColor){
        this.setText(title);
        this.setBackground(backgroundColor);
        this.setForeground(Color.WHITE);
        this.setFont(new Font(Font.DIALOG,Font.BOLD+Font.ITALIC,20));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setOpaque(true);
    }
}
