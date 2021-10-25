import javax.swing.*;
import java.awt.*;

public class SnakeBubble extends JPanel {
    JFrame frame;
    int xPos, yPos;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(new Color(180,30,00));
        g.fillOval(xPos,yPos,15,15);
    }
    SnakeBubble(){
        frame = new JFrame();

        setBorder(BorderFactory.createLineBorder(Color.green,5));
        setBackground(Color.BLACK);
        setSize(600,600);

        frame.add(this);
        frame.setResizable(false);
        frame.setSize(600,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
