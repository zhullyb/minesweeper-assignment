package src;

import javax.swing.*;
import java.awt.*;

public class GameWin extends JFrame {

    int width = GameUtil.OFFSET * 2 + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = GameUtil.OFFSET * 4 + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    MapBottom mapBottom = new MapBottom();

    void launch() {
        this.setSize(width, height);
        this.setLocationRelativeTo(null);
        this.setTitle("扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        while(true){
            repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        mapBottom.paintSelf(g);
    }

    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
