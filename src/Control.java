import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Random;

public class Control extends SnakeBubble implements KeyListener {
    int x = 90, y = 15, snake = 3, score = 0;
    boolean apple = true;
    int[] bodyX= new int[1500]; int[] bodyY= new int[1500];
    String keypress = "right", status = "right";

    Control(){
        addKeyListener(this);
        frame.addKeyListener(this);
    }

    Timer time = new Timer(120, e->{
        win();
        swap();
        movement();
        setApple();
        loose();
    });
    void swap(){
        for (int i = snake-1; i > 0; i--) {
            bodyX[i] = bodyX[i-1];
            bodyY[i] = bodyY[i-1];
            x = bodyX[i];
            y = bodyY[i];
        }
    }
    void movement(){
        if (keypress == "right")
            x+=15;
        if (keypress == "left")
            x-=15;
        if (keypress == "down")
            y+=15;
        if (keypress == "up")
            y-=15;
    }
    void setApple(){
        Random rand = new Random();
        while(apple){
            xPos = 15+ rand.nextInt(400-15);
            yPos = 15 + rand.nextInt(400-15);
            if (xPos%15 == 0 && yPos%15 == 0){
                for (int i = 0; i < snake; i++) {
                    if (xPos != bodyX[i] && yPos != bodyY[i]){
                        apple=false;
                    }
                }
            }
        }
    }
    void win(){
        if (xPos == x && yPos == y){
            apple = true;
            snake++;
            score++;
        }
//        swap();
    }
    void message(){
        int a = JOptionPane.showConfirmDialog(this, "Play Again "+score);
        System.out.println(a);
        if (a > -2){
            try {
                BufferedInputStream read = new BufferedInputStream(new FileInputStream("src/score.txt"));
                int r = read.read();
                String txt = "";
                while(r!=-1){
                    txt += (char)r;
                    r = read.read();
                }
                int x = Integer.parseInt(txt);
                FileWriter writ = new FileWriter(new File("src/score.txt"));
//                if (score > x) {
                    writ.write(String.valueOf(score));
//                }
                writ.close();
            }
            catch (Exception ex){}
//            time.start();
//            x = 90; y = 15; snake = 3;
//            score = 0;
//            keypress="right";
        }else {
            time.stop();
        }
    }
    void loose(){
        if(x >= 600 || x<= -15 || y >= 515 || y<=-15){
            message();
        }
        for (int i = 1; i < snake; i++) {
            if (bodyX[i] == x && bodyY[i] == y){
                message();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE);
        bodyX[0] = x;
        bodyY[0] = y;
        for (int i = snake; i > 0; i--) {
            g.drawOval(bodyX[i-1],bodyY[i-1],15,15);
        }
        Graphics2D g2D = (Graphics2D)g;
        g2D.fillOval(bodyX[0],bodyY[0],15,15);
        g2D.setColor(Color.BLUE);
        repaint();
    }

    public static void main(String[] args) {
        new Control().time.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP)
            keypress = "up";
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            keypress = "down";
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            keypress = "left";
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            keypress = "right";
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
